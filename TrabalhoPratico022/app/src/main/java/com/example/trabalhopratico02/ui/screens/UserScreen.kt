package com.example.trabalhopratico02.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trabalhopratico02.data.models.User
import com.example.trabalhopratico02.viewmodel.PostViewModel

@Composable
fun UserScreen(viewModel: PostViewModel = viewModel(), modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var editingUser by remember { mutableStateOf<User?>(null) }
    val context = LocalContext.current

    // Gradiente vertical (mesma paleta da PostScreen)
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE0F7FA), // azul claro
            Color(0xFFB2EBF2)  // tom mais intenso
        )
    )

    LaunchedEffect(Unit) {
        isLoading = true
        viewModel.fetchUsers()
        isLoading = false
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(16.dp)
    ) {
        Column {
            // Card para os campos de criação de usuário
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = 4.dp,
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text(text = "Nome") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            isLoading = true
                            viewModel.createUser(
                                name = name,
                                email = email,
                                onSuccess = {
                                    Toast.makeText(context, "Usuário criado com sucesso!", Toast.LENGTH_SHORT).show()
                                    isLoading = false
                                },
                                onError = {
                                    Toast.makeText(context, "Erro ao criar usuário!", Toast.LENGTH_SHORT).show()
                                    isLoading = false
                                }
                            )
                            // Limpa campos
                            name = ""
                            email = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFFB2DFDB),
                            contentColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Criar Usuário")
                    }
                }
            }

            // Lista de usuários ou indicador de carregamento
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(viewModel.users) { user ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            elevation = 4.dp,
                            backgroundColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Nome: ${user.name}",
                                    style = MaterialTheme.typography.h6,
                                    color = Color(0xFF00796B) // cor de destaque
                                )
                                Text(
                                    text = "Email: ${user.email}",
                                    style = MaterialTheme.typography.body1,
                                    color = Color.DarkGray
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // Diálogo de edição do usuário
    if (editingUser != null) {
        AlertDialog(
            onDismissRequest = { editingUser = null },
            title = { Text(text = "Editar Usuário") },
            text = {
                Column {
                    OutlinedTextField(
                        value = editingUser!!.name,
                        onValueChange = { newName ->
                            editingUser = editingUser!!.copy(name = newName)
                        },
                        label = { Text(text = "Nome") },
                        shape = RoundedCornerShape(8.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = editingUser!!.email,
                        onValueChange = { newEmail ->
                            editingUser = editingUser!!.copy(email = newEmail)
                        },
                        label = { Text(text = "Email") },
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // Aqui seria "updateUser" caso você tenha esse método no ViewModel
                        // Mas, seguindo o mesmo modelo do seu código...
                        viewModel.updatePost(
                            editingUser!!.id,
                            editingUser!!.name,
                            editingUser!!.email
                        )
                        editingUser = null
                    }
                ) {
                    Text(text = "Salvar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { editingUser = null }
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
private fun UserScreenPreview() {
    UserScreen()
}
