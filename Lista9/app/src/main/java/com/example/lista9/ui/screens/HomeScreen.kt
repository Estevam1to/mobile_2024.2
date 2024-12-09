package com.example.lista9.ui.screens

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.lista9.models.eventList
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

@Composable
fun HomeScreen(
    navController: NavHostController,
    context: Context // Passamos o contexto para criar notificações
) {
    RequestNotificationPermission(context)

    // Cria o canal de notificação ao exibir a tela
    LaunchedEffect(Unit) {
        createNotificationChannel(context)
        Log.d("HOMESCREEN", "CANAL DE NOTIFICAÇÃO CRIADO")
    }

    // Filtra os eventos em que o usuário está inscrito
    val subscribedEvents = eventList.filter { it.isSubscribed.value }

    Column {
        // Se houver eventos inscritos, exibe-os em uma LazyRow
        if (subscribedEvents.isNotEmpty()) {
            Text(
                text = "Eventos Inscritos",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(16.dp)
            )

            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(subscribedEvents) { event ->
                    Card(
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                navController.navigate("eventDetails/${event.id}")
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = event.imageRes),
                            contentDescription = event.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Seção principal com a lista de todos os eventos
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(eventList) { event ->
                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable {
                            navController.navigate("eventDetails/${event.id}")
                        },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            // Imagem do evento
                            Image(
                                painter = painterResource(id = event.imageRes),
                                contentDescription = event.title,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = event.title,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = event.location,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }

                            // Ícone de favorito
                            Icon(
                                imageVector = if (event.isFavorite.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Favorito",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(24.dp)
                                    .clickable {
                                        // Alterna o estado de favorito
                                        event.isFavorite.value = !event.isFavorite.value
                                    }
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Descrição do evento
                        Text(
                            text = event.description,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        // Botão "Se Inscrever" ou "Inscrito"
                        Button(
                            onClick = {
                                // Alterna o estado de inscrição
                                event.isSubscribed.value = !event.isSubscribed.value
                                if (event.isSubscribed.value) {
                                    // Envia notificação usando a função abaixo
                                    sendNotification(context, event.title)
                                    Log.d("HOMESCREEN", "SOLICITOU ENVIO DE NOTIFICAÇÃO")
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (event.isSubscribed.value) "Inscrito" else "Se Inscrever"
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        // Botão "Ver mais sobre"
                        Button(
                            onClick = { navController.navigate("eventDetails/${event.id}") },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Ver mais sobre ${event.title}")
                        }
                    }
                }
            }
        }
    }
}

// Função para criar o canal de notificação
private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "Inscrição de Eventos"
        val descriptionText = "Canal para notificações de inscrição em eventos"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("EVENT_CHANNEL", name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

@Composable
fun RequestNotificationPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.d("PERMISSION", "Permissão de notificação concedida")
            } else {
                Log.e("PERMISSION", "Permissão de notificação negada")
            }
        }

        LaunchedEffect(Unit) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }
}


// Função para enviar notificações
private fun sendNotification(context: Context, eventTitle: String) {
    val builder = NotificationCompat.Builder(context, "EVENT_CHANNEL")
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle("Inscrição Confirmada")
        .setContentText("Você foi inscrito no evento: $eventTitle")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    with(NotificationManagerCompat.from(context)) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            androidx.core.app.ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) != android.content.pm.PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        Log.d("HOMESCREEN", "CHEGOU NO FINAL DA FUNÇÃO DE NOTIFICAÇÃO")
        notify(eventTitle.hashCode(), builder.build())
    }
}
