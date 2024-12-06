package com.example.lista8.navigation

import CharacterViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lista8.models.characterList
import com.example.lista8.ui.screens.DetailsScreen
import com.example.lista8.ui.screens.FavoritesScreen
import com.example.lista8.ui.screens.HomeScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: CharacterViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(
                onCharacterSelected = { character ->
                    navController.navigate("details/${character.id}")
                },
                onFavoriteToggle = { character ->
                    viewModel.toggleFavorite(character)
                },
                characters = viewModel.characters
            )
        }

        composable("favorites") {
            FavoritesScreen(
                onCharacterSelected = { character ->
                    navController.navigate("details/${character.id}")
                },
                onFavoriteToggle = { character ->
                    viewModel.toggleFavorite(character)
                },
                characters = viewModel.characters.filter { it.isFavorite }
            )
        }

        composable(
            route = "details/{characterId}",
            arguments = listOf(navArgument("characterId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: 0
            val character = viewModel.getCharacterById(characterId)

            if (character != null) {
                DetailsScreen(character)
            } else {
                Text("Personagem não encontrado")
            }
        }
    }
}

