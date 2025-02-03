package com.example.mobile_computing.ui.conversation_app

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mobile_computing.R

@Composable
fun ProfileScreen(viewModel: ConversationAppViewModel) {

    var inProfileEditor by remember { mutableStateOf(false) }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedImageUrl by remember { mutableStateOf<String?>(null) }

    val useUrl by viewModel.useUrl.collectAsState()
    val users by viewModel.users.collectAsState()
    val savedImageBitmap by viewModel.savedImageBitmap.collectAsState()

    val context = LocalContext.current

    val imagePickerLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            selectedImageUri = uri

        }

    var showProfileNameEditor by remember { mutableStateOf(false) }
    var showProfilePictureEditor by remember { mutableStateOf(false) }



    SideEffect {
        viewModel.loadSavedImage(context)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        if (useUrl) {
            if (inProfileEditor) {
                if (selectedImageUrl != null) {
                    AsyncImage(
                        model = selectedImageUrl,
                        contentDescription = "Profile",
                        modifier = Modifier.size(200.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.mazda_logo),
                        contentDescription = "Profile Placeholder",
                        modifier = Modifier.size(200.dp)
                    )
                }
            } else {
                val profileUrl = users.firstOrNull()?.profileUrl
                if (!profileUrl.isNullOrEmpty()) {
                    AsyncImage(
                        model = profileUrl,
                        contentDescription = "Profile",
                        modifier = Modifier.size(200.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.mazda_logo),
                        contentDescription = "Profile Placeholder",
                        modifier = Modifier.size(200.dp)
                    )
                }
            }

        } else {
            if (inProfileEditor) {

                if (selectedImageUri != null) {
                    Image(
                        bitmap = viewModel.loadBitmapFromUri(context, selectedImageUri!!)!!
                            .asImageBitmap(),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.mazda_logo),
                        contentDescription = "Profile Placeholder",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            } else {
                if (savedImageBitmap != null) {
                    savedImageBitmap?.let {
                        Image(
                            bitmap = savedImageBitmap!!.asImageBitmap(),
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .size(200.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.mazda_logo),
                        contentDescription = "Profile Placeholder",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }

        }


        Spacer(modifier = Modifier.height(20.dp))

        Text(users.firstOrNull()?.name ?: "No Name")

        Spacer(modifier = Modifier.height(20.dp))


        Row {
            Button(onClick = {
                showProfileNameEditor = true
            }) {
                Text("Edit Profile Name")
            }

            Button(onClick = {
                showProfilePictureEditor = true
                inProfileEditor = true
            }) {
                Text("Edit Profile Picture")
            }
        }

        if (showProfileNameEditor) {
            ProfileNameEditor(currentName = users.firstOrNull()?.name ?: "", onNameChanged = {
                viewModel.addUser(name = it, profileUrl = users.firstOrNull()?.profileUrl)
                showProfileNameEditor = false
            })
        }

        if (showProfilePictureEditor) {
            ProfilePictureEditor(
                onPickFromGalleryClicked = { imagePickerLauncher.launch("image/*") },
                onPickFromGallerySaveClicked = {
                    viewModel.saveSelectedImageFromGallery(context, selectedImageUri)
                    showProfilePictureEditor = false
                    inProfileEditor = false
                },
                onLoadFromUrlClicked = {
                    selectedImageUrl = it
                },
                onLoadFromUrlSaveClicked = {
                    viewModel.addUser(name = users.firstOrNull()?.name ?: "", profileUrl = it)
                    showProfilePictureEditor = false
                    inProfileEditor = false
                },
                onUseUrlChanged = {
                    viewModel.setUserUrlProfile(it)
                },
                useUrlProfile = useUrl,
                onCancelClicked = {
                    showProfilePictureEditor = false
                    inProfileEditor = false
                }
            )
        }
    }

}

@Composable
fun ProfileNameEditor(
    currentName: String,
    onNameChanged: (String) -> Unit,
) {

    var urlInput by remember { mutableStateOf(currentName) }

    Column {
        OutlinedTextField(
            value = urlInput,
            onValueChange = { urlInput = it },
            label = { Text("Enter Your Name") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            onNameChanged(urlInput)
        }) {
            Text("Save")
        }

    }
}

@Composable
fun ProfilePictureEditor(
    onPickFromGallerySaveClicked: () -> Unit,
    onPickFromGalleryClicked: () -> Unit,
    onLoadFromUrlSaveClicked: (String) -> Unit,
    onLoadFromUrlClicked: (String) -> Unit,
    onUseUrlChanged: (Boolean) -> Unit,
    useUrlProfile: Boolean,
    onCancelClicked: () -> Unit
) {
    var urlInput by remember { mutableStateOf("") }

    Column {

        Row {
            Text("Load From URL")
            Checkbox(
                checked = useUrlProfile,
                onCheckedChange = {
                    onUseUrlChanged(it)
                }
            )
        }

        if (useUrlProfile) {
            // Enter URL for Image
            OutlinedTextField(
                value = urlInput,
                onValueChange = { urlInput = it },
                label = { Text("Enter Image URL") },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                onLoadFromUrlClicked(urlInput)

            }) {
                Text("Load Image from URL")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                onLoadFromUrlSaveClicked(urlInput)
            }) {
                Text("Save")
            }

            Button(onClick = {
                onCancelClicked()
            }) {
                Text("Cancel")
            }

        }

        if (!useUrlProfile) {
            Button(onClick = {
                onPickFromGalleryClicked()
            }) {
                Text("Pick Image from Gallery")
            }

            Button(onClick = {
                onPickFromGallerySaveClicked()
            }) {
                Text("Save")
            }

            Button(onClick = {
                onCancelClicked()
            }) {
                Text("Cancel")
            }
        }


    }
}