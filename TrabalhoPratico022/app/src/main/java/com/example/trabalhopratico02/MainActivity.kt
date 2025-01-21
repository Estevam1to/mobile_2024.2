package com.example.trabalhopratico02

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.trabalhopratico02.ui.screens.PostScreen
import com.example.trabalhopratico02.ui.screens.UserScreen
import com.example.trabalhopratico02.ui.theme.TrabalhoPratico02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE0F7FA),  // Azul claro
            Color(0xFFB2EBF2)   // Tom mais intenso
        )
    )

    // Cor para a topBar e bottomBar (combina com a paleta)
    val appBarColor = Color(0xFF00796B) // Verde/azul escuro
    val appBarContentColor = Color.White

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground) // gradiente de fundo
    ) {
        // O Scaffold fica "por cima" do gradiente
        Scaffold(
            // Barra superior
            topBar = {
                TopAppBar(
                    title = { Text("PostAPP") },
                    backgroundColor = appBarColor,
                    contentColor = appBarContentColor
                )
            },
            // Barra inferior
            bottomBar = {
                BottomNavigation(
                    backgroundColor = appBarColor,
                    contentColor = appBarContentColor
                ) {
                    BottomNavigationItem(
                        selected = selectedTab == 0,
                        onClick = { selectedTab = 0 },
                        label = { Text("Usuários") },
                        icon = { Icon(Icons.Default.Person, contentDescription = "Usuários") },
                        alwaysShowLabel = true
                    )
                    BottomNavigationItem(
                        selected = selectedTab == 1,
                        onClick = { selectedTab = 1 },
                        label = { Text("Posts") },
                        icon = { Icon(Icons.Default.List, contentDescription = "Posts") },
                        alwaysShowLabel = true
                    )
                }
            },
            backgroundColor = Color.Transparent  // Faz o Scaffold ficar "transparente" sobre o gradiente
        ) {
            // Conteúdo principal
            when(selectedTab) {
                0 -> UserScreen()
                1 -> PostScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TrabalhoPratico02Theme {
        MainScreen()
    }
}
