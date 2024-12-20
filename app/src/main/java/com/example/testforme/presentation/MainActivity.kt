package com.example.testforme.presentation

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.example.testforme.domain.repository.UserRepository
import com.example.testforme.presentation.navigation.Navigation
import com.example.testforme.presentation.ui.theme.TestForMeTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestForMeTheme {
                val context = LocalContext.current
                UserRepository.init(context)
                val controller = rememberNavController()

                Scaffold() {Navigation(controller)}
            }
        }
    }
}
