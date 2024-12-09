package com.example.lista9.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.lista9.R

data class Event(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val location: String,
    val isFavorite: MutableState<Boolean> = mutableStateOf(false),
    val isSubscribed: MutableState<Boolean> = mutableStateOf(false),
    val imageRes: Int
)

val eventList = mutableListOf(
        Event(
            id = 1,
            title = "Conferência de Tecnologia 2024",
            description = "Tendências em tecnologia.",
            date = "2024-12-15",
            location = "Parque Tecnológico",
            isFavorite = mutableStateOf(false),
            isSubscribed = mutableStateOf(false),
            imageRes = R.drawable.img1
        ),
        Event(
            id = 2,
            title = "Feira de Inovação Científica",
            description = "Exposição de projetos científicos de estudantes.",
            date = "2024-12-20",
            location = "Centro de Convenções",
            isFavorite = mutableStateOf(false),
            isSubscribed = mutableStateOf(false),
            imageRes = R.drawable.img2
        ),
        Event(
            id = 3,
            title = "Workshop de Desenvolvimento Mobile",
            description = "Aprenda a criar aplicativos do zero.",
            date = "2024-12-22",
            location = "Coworking Central",
            isFavorite = mutableStateOf(false),
            isSubscribed = mutableStateOf(false),
            imageRes = R.drawable.img3
        ),
        Event(
            id = 4,
            title = "Hackathon - Desafios em IA",
            description = "Resolva problemas reais usando inteligência artificial.",
            date = "2024-12-18",
            location = "Campus Universitário",
            isFavorite = mutableStateOf(false),
            isSubscribed = mutableStateOf(false),
            imageRes = R.drawable.img4
        ),
        Event(
            id = 5,
            title = "Simpósio de Cibersegurança",
            description = "Palestras com especialistas sobre segurança digital.",
            date = "2024-12-25",
            location = "Auditório Municipal",
            isFavorite = mutableStateOf(false),
            isSubscribed = mutableStateOf(false),
            imageRes = R.drawable.img5
        ),
        Event(
            id = 6,
            title = "Mostra de Robótica Educacional",
            description = "Apresentação de robôs criados por estudantes do ensino médio.",
            date = "2024-12-30",
            location = "Escola Técnica Estadual",
            isFavorite = mutableStateOf(false),
            isSubscribed = mutableStateOf(false),
            imageRes = R.drawable.img6
        )
    )
