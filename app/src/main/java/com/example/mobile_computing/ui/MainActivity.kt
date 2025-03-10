package com.example.mobile_computing.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.mobile_computing.ui.theme.Mobile_ComputingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }


        Log.d("Alireza", "oncreate: ")

//        checkNotificationPermissionAndStartService()

    }

//    private fun checkNotificationPermissionAndStartService() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            // Check if permission is granted
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
//                // Permission is granted, start the service
//                startPickupDetectorService()
//            } else {
//                // Request permission
//                Log.d("Alireza", "checkNotificationPermissionAndStartService: ")
//                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
//            }
//        } else {
//            // No need to request permission below Android 13
//            startPickupDetectorService()
//        }
//    }
//
//    private val requestPermissionLauncher = registerForActivityResult(
//        ActivityResultContracts.RequestPermission()
//    ) { isGranted: Boolean ->
//        if (isGranted) {
//            // Permission granted, start the service
//            startPickupDetectorService()
//        } else {
//            // Permission denied, you can show a message to the user
//        }
//    }

//    private fun startPickupDetectorService() {
//        Intent(this, PickupDetectorService::class.java).also { intent ->
//            startService(intent)
//        }
//    }


}


@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Mobile_ComputingTheme {
        val navController = rememberNavController()
        Scaffold { innerPadding ->
            AppNavHost(navController, modifier.padding(innerPadding))
        }


    }
}



