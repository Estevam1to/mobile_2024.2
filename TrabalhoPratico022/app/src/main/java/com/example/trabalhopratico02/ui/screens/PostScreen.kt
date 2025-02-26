package com.example.trabalhopratico02.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trabalhopratico02.data.models.Post
import com.example.trabalhopratico02.viewmodel.PostViewModel

@Composable
fun PostScreen(viewModel: PostViewModel = viewModel(), modifier: Modifier = Modifier) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var editingPost by remember { mutableStateOf<Post?>(null) }
    val context = LocalContext.current

    // Gradiente vertical suave
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE0F7FA), // azul claro
            Color(0xFFB2EBF2)  // tom mais intenso
        )
    )

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.fetchPosts()
        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(16.dp)
    ) {
        Column {
            // Card com os campos de criação de post
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text(text = "Título") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = content,
                        onValueChange = { content = it },
                        label = { Text(text = "Conteúdo") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            isLoading = true
                            viewModel.createPost(
                                title,
                                content,
                                onSuccess = {
                                    Toast.makeText(
                                        context,
                                        "Post criado com sucesso!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    isLoading = false
                                },
                                onError = {
                                    Toast.makeText(
                                        context,
                                        "Erro ao criar post!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    isLoading = false
                                }
                            )
                            // Limpa campos
                            title = ""
                            content = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFB2DFDB),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Criar Post")
                    }
                }
            }

            // Lista de posts ou indicador de carregamento
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(viewModel.posts) { post ->
                        PostItem(
                            post = post,
                            onDelete = { viewModel.deletePost(it) },
                            onEdit = { editingPost = it }
                        )
                    }
                }
            }
        }
    }

    // Diálogo de Edição de Post
    if (editingPost != null) {
        AlertDialog(
            onDismissRequest = { editingPost = null },
            title = { Text(text = "Editar Post") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editingPost!!.title,
                        onValueChange = { newTitle ->
                            editingPost = editingPost!!.copy(title = newTitle)
                        },
                        label = { Text(text = "Título") },
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editingPost!!.content,
                        onValueChange = { newContent ->
                            editingPost = editingPost!!.copy(content = newContent)
                        },
                        label = { Text(text = "Conteúdo") },
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.updatePost(
                            editingPost!!.id,
                            editingPost!!.title,
                            editingPost!!.content
                        )
                        editingPost = null
                    }
                ) {
                    Text(text = "Salvar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { editingPost = null }
                ) {
                    Text(text = "Cancelar")
                }
            },
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White
        )
    }
}

@Preview
@Composable
private fun PostScreenPreview() {
    PostScreen()
}
