package com.example.mobile_computing.ui.conversation_app

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobile_computing.data.conversation_app.MessageDao
import com.example.mobile_computing.data.conversation_app.MessageEntity
import com.example.mobile_computing.data.conversation_app.UserDao
import com.example.mobile_computing.data.conversation_app.UserEntity
import com.example.mobile_computing.data.conversation_app.UserPreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ConversationAppViewModel @Inject constructor(
    private val userDao: UserDao,
    messageDao: MessageDao,
    private val userPreferencesRepository: UserPreferenceRepository
) : ViewModel() {


    val users: StateFlow<List<UserEntity>> = userDao.getAllUsers()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val messages: StateFlow<List<MessageEntity>> = messageDao.getAllMessages()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val useUrl: StateFlow<Boolean> = userPreferencesRepository.useUrlProfileFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, false)

    private val _savedImageBitmap = MutableStateFlow<Bitmap?>(null)
    val savedImageBitmap: StateFlow<Bitmap?> = _savedImageBitmap


    // Function to load Bitmap from URI
    fun loadBitmapFromUri(context: Context, uri: Uri): Bitmap? {
        return try {
            val contentResolver: ContentResolver = context.contentResolver
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // ImageDecoder for Android 10+
                val source = ImageDecoder.createSource(contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                // BitmapFactory for older versions
                MediaStore.Images.Media.getBitmap(contentResolver, uri)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Function to save image to app-specific storage
    fun saveImageToAppStorage(context: Context, imageBitmap: Bitmap?) {
        imageBitmap?.let { bitmap ->
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val file = File(context.filesDir, "saved_image.jpg")
                    val outputStream = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream)
                    outputStream.flush()
                    outputStream.close()
                    Log.d("ImagePickerViewModel", "Image saved at: ${file.absolutePath}")
                } catch (e: Exception) {
                    Log.e("ImagePickerViewModel", "Error saving image: ${e.message}")
                }
            }
        }
    }

     fun loadSavedImage(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val file = File(context.filesDir, "saved_image.jpg")
            if (file.exists()) {
                val bitmap = BitmapFactory.decodeFile(file.absolutePath)
                _savedImageBitmap.value = bitmap
            } else {
                Log.e("ImagePickerViewModel", "No saved image found")
            }
        }
    }

    fun addUser(name: String, profileUrl: String?) {
        viewModelScope.launch {
            userDao.insertUser(UserEntity(id = 0, name = name, profileUrl = profileUrl))
        }
    }


    fun saveSelectedImageFromGallery(context: Context, imageUri: Uri?) {
        imageUri?.let {
            saveImageToAppStorage(context, loadBitmapFromUri(context, it))
            loadSavedImage(context)
        }
    }

    fun setUserUrlProfile(enabled: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setUseUrlProfile(enabled)
        }
    }


}