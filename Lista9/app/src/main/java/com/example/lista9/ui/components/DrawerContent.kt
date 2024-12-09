package com.example.lista9.ui.components

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DrawerContent(
    navController: NavHostController,
    onSendNotification: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .width(380.dp)
            .background(MaterialTheme.colorScheme.surface),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Menu",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .semantics { contentDescription = "Título do Menu" }
            )
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            MenuItem(
                text = "Perfil",
                onClick = {
                    navController.navigate("perfil")
                }
            )

            MenuItem(
                text = "Notificação",
                onClick = onSendNotification
            )

            ExitMenuItem()

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Versão 0.0.0.1",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun ExitMenuItem() {
    val context = LocalContext.current
    MenuItem(
        text = "Sair",
        color = MaterialTheme.colorScheme.error,
        onClick = {
            val activity = (context as? Activity)
            activity?.finishAffinity() 
            println("Aplicativo fechado")
        }
    )
}


@Composable
fun MenuItem(
    text: String,
    onClick: () -> Unit,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurface
) {
    Text(
        text = text,
        color = color,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp)
            .semantics { contentDescription = text }
    )
}
