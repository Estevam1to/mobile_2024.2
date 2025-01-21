package com.example.trabalhopratico02.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.trabalhopratico02.data.models.Post

@Composable
fun PostItem(
    post: Post,
    onDelete: (Int) -> Unit,
    onEdit: (Post) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),             // Cantos arredondados
        elevation = 6.dp,                              // Sombra sob o card
        border = BorderStroke(1.dp, Color.LightGray),  // Borda suave
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Título do Post
            Text(
                text = post.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Conteúdo do Post
            Text(
                text = post.content,
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botões (Deletar / Editar)
            Row {
                Button(
                    onClick = { showDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFFFCDD2), // Cor de fundo (rosa claro)
                        contentColor = Color.Black          // Cor do texto
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(text = "Deletar")
                }

                Button(
                    onClick = { onEdit(post) },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFB2EBF2), // Cor de fundo (azul claro)
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Editar")
                }
            }
        }
    }

    // Diálogo de confirmação para deletar
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = "Confirmar Exclusão") },
            text = { Text(text = "Tem certeza que deseja deletar este post?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDelete(post.id)
                        showDialog = false
                    }
                ) {
                    Text(text = "Sim")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text(text = "Não")
                }
            },
            // Rounded corners no AlertDialog
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White
        )
    }
}
