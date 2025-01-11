package com.example.crudapp2

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                UserApp(viewModel = viewModel)
            }
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UserApp(viewModel: UserViewModel) {

    val usuarios by viewModel.users.observeAsState(emptyList())

    var nome by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }

    var usuarioEditando by remember { mutableStateOf<User?>(null) }

    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Gerenciamento de Usuários") },
                backgroundColor = MaterialTheme.colors.primary,
                contentColor = Color.White
            )
        }
    ) {
        // Coluna que envolve o conteúdo principal da tela.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Título que muda dependendo se estamos adicionando ou editando um usuário
            Text(
                text = if (usuarioEditando == null) "Adicionar um novo usuário" else "Editar usuário",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Campo de texto para o nome do usuário
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text(text = "Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para a idade do usuário (com teclado numérico)
            OutlinedTextField(
                value = idade,
                onValueChange = { idade = it },
                label = { Text(text = "Idade") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botão para adicionar ou salvar alterações de um usuário
            Button(
                onClick = {
                    try {
                        // Verifica se os campos estão preenchidos
                        if (nome.isNotEmpty() && idade.isNotEmpty()) {
                            val idadeInt = idade.toInt() // Converte a idade para Int
                            if (usuarioEditando == null) {
                                // Se não há usuário em edição, adiciona um novo
                                viewModel.addUser(User(name = nome, age = idadeInt))
                            } else {
                                // Se há usuário em edição, atualiza os dados
                                viewModel.updateUser(
                                    usuarioEditando!!.copy(
                                        name = nome,
                                        age = idadeInt
                                    )
                                )
                                usuarioEditando = null // Limpa o estado de edição
                            }
                            // Reseta campos e mensagem de erro
                            nome = ""
                            idade = ""
                            mensagemErro = null
                        } else {
                            mensagemErro = "Preencha todos os campos!"
                        }
                    } catch (e: NumberFormatException) {
                        // Caso a idade não seja um número válido
                        mensagemErro = "Idade inválida. Digite apenas números."
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                // Texto do botão muda se estamos adicionando ou editando
                Text(if (usuarioEditando == null) "Adicionar usuário" else "Salvar alterações")
            }

            // Exibe a mensagem de erro, se houver
            mensagemErro?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    it,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            // Título da lista de usuários cadastrados
            Text(
                text = "Lista de Usuários",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colors.primary
            )

            // Exibe a lista de usuários em forma de LazyColumn
            LazyColumn {
                items(usuarios) { user ->
                    // Cada usuário é mostrado em uma linha (Row)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Texto com o nome e a idade
                        Text(
                            text = "• ${user.name}, ${user.age} anos",
                            fontSize = 16.sp
                        )

                        // Espaço expansível para empurrar o botão "Editar" para a direita
                        Spacer(modifier = Modifier.weight(1f))

                        // Botão para editar o usuário
                        OutlinedButton(
                            onClick = {
                                usuarioEditando = user
                                nome = user.name
                                idade = user.age.toString()
                            }
                        ) {
                            Text("Editar")
                        }
                    }
                }
            }
        }
    }
}
