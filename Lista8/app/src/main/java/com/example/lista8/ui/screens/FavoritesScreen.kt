package com.example.lista8.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lista8.models.Character
import com.example.lista8.models.characterList
import com.example.lista8.ui.components.CharacterListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onCharacterSelected: (Character) -> Unit,
    onFavoriteToggle: (Character) -> Unit,
    characters: List<Character>
) {
    val favoriteCharacters = characters

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Favoritos") }
            )
        }
    ) { paddingValues ->
        if (favoriteCharacters.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Você ainda não adicionou nenhum personagem aos favoritos.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoriteCharacters) { character ->
                    CharacterListItem(
                        character = character,
                        onCharacterSelected = onCharacterSelected,
                        onFavoriteToggle = onFavoriteToggle
                    )
                }
            }
        }
    }
}
