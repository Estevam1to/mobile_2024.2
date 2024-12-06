package com.example.lista8

import CharacterViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lista8.navigation.MainNavigation
import com.example.lista8.ui.theme.Lista8Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lista8Theme {
                val viewModel: CharacterViewModel = viewModel()
                MainNavigation(viewModel)
            }
        }
    }
}
