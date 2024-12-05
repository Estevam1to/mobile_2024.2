package com.example.lista7.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lista7.models.characterList
import com.example.lista7.ui.screens.CharacterScreen
import com.example.lista7.ui.screens.HomeScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onCharacterSelected = { character ->
                navController.navigate("details/${character.id}")
            })
        }
        composable("details/{characterId}") { backStackEntry ->
            val characterId = backStackEntry.arguments?.getString("characterId")?.toInt()
            val character = characterList.firstOrNull { it.id == characterId }
            character?.let {
                CharacterScreen(character = it)
            }
        }
    }
}


