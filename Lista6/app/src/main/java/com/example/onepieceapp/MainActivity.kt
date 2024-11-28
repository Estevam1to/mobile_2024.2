package com.example.onepieceapp

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.onepieceapp.ui.theme.OnePieceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnePieceAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        onPersonagemSelected = { personagem ->
                            val intent = Intent(this, PersonagemActivity::class.java)
                            intent.putExtra("personagem", personagem)
                            startActivity(intent)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class PersonagemActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val personagem = intent.getStringExtra("personagem") ?: "Zoro"

        setContent {
            OnePieceAppTheme {
                PersonagemScreen(personagem)
            }
        }
    }
}

class VideoPlayerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val videoRes = intent.getIntExtra("videoRes", R.raw.sanji_video)
        setContent {
            AndroidView(
                factory = { context ->
                    VideoView(context).apply {
                        setVideoURI(Uri.parse("android.resource://$packageName/$videoRes"))
                        start()
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
@Composable
fun OnePieceAppMenu(onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true }) {
        Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("Zoro") },
            onClick = {
                expanded = false
                onOptionSelected("Zoro")
            }
        )
        DropdownMenuItem(
            text = { Text("Sanji") },
            onClick = {
                expanded = false
                onOptionSelected("Sanji")
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onPersonagemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("OnePieceApp") },
                actions = { OnePieceAppMenu(onOptionSelected = onPersonagemSelected) }
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text("Selecione um personagem no menu.")
        }
    }
}

@Composable
fun PersonagemScreen(personagem: String) {
    val context = LocalContext.current
    val imageRes = if (personagem == "Sanji") R.drawable.sanji_imagem else R.drawable.zoro_imagem
    val soundRes = if (personagem == "Sanji") R.raw.sanji else R.raw.zoro
    val videoRes = if (personagem == "Sanji") R.raw.sanji_video else R.raw.zoro_video

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "$personagem Image",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            try {
                val mediaPlayer = MediaPlayer.create(context, soundRes)
                mediaPlayer?.start()
                mediaPlayer?.setOnCompletionListener { it.release() }
            } catch (e: Exception) {
                println("Erro ao reproduzir som: ${e.message}")
            }
        }) {
            Text("Reproduzir Som")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context, VideoPlayerActivity::class.java)
            intent.putExtra("videoRes", videoRes)
            context.startActivity(intent)
        }) {
            Text("Reproduzir Vídeo")
        }
    }
}
