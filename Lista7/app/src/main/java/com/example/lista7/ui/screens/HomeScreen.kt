package com.example.lista7.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lista7.models.Character
import com.example.lista7.models.characterList
import com.example.lista7.ui.components.CharacterListItem

@Composable
fun HomeScreen(onCharacterSelected: (Character) -> Unit) {
    var searchQuery by remember { mutableStateOf("") } // Estado para o texto do campo de pesquisa
    val filteredCharacters = remember(searchQuery) {
        characterList.filter {
            it.name.contains(searchQuery, ignoreCase = true)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Adiciona um espaçamento no topo
        Spacer(modifier = Modifier.height(40.dp)) // Espaço inicial

        // Campo de pesquisa
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Pesquisar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp) // Padding lateral
        )

        // Espaçamento entre o campo de pesquisa e a lista
        Spacer(modifier = Modifier.height(5.dp))

        // Lista de personagens filtrados
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredCharacters) { character ->
                CharacterListItem(character, onCharacterSelected)
            }
        }
    }
}



