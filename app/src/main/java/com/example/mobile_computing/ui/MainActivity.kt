package com.example.mobile_computing.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.mobile_computing.ui.theme.Mobile_ComputingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarApp()
        }
    }
}

@Composable
fun CarApp(modifier: Modifier = Modifier) {
    Mobile_ComputingTheme {
        val navController = rememberNavController()
        Scaffold { innerPadding ->
            AppNavHost(navController, modifier.padding(innerPadding))
        }

    }
}



