package com.example.lista7.models

import com.example.lista7.R


data class Character(
    val id: Int,
    val name: String,
    val role: String,
    val imageRes: Int,
    val description: String,
    val bounty: String,
    var isFavorite: Boolean = false
)

val characterList = listOf(
    Character(
        1,
        "Monkey D. Luffy",
        "Captain",
        R.drawable.luffy,
        "O protagonista e capitão dos Chapéus de Palha.",
        "1,500,000,000 Berries"
    ),
    Character(
        2,
        "Roronoa Zoro",
        "Swordsman",
        R.drawable.zoro,
        "O espadachim e braço direito de Luffy.",
        "320,000,000 Berries"
    ),
    Character(
        3,
        "Nami",
        "Navigator",
        R.drawable.nami,
        "A navegadora habilidosa da tripulação e amante de mapas.",
        "66,000,000 Berries"
    ),
    Character(
        4,
        "Usopp",
        "Sniper",
        R.drawable.usopp,
        "O franco-atirador e contador de histórias da tripulação.",
        "200,000,000 Berries"
    ),
    Character(
        5,
        "Sanji",
        "Chef",
        R.drawable.sanji,
        "O cozinheiro e mestre em chutes da tripulação.",
        "330,000,000 Berries"
    ),
    Character(
        6,
        "Tony Tony Chopper",
        "Doctor",
        R.drawable.chopper,
        "O médico e mascote da tripulação, uma rena que comeu a Hito Hito no Mi.",
        "100 Berries"
    ),
    Character(
        7,
        "Nico Robin",
        "Archaeologist",
        R.drawable.robin,
        "A arqueóloga e única sobrevivente de Ohara, especialista em Poneglyphs.",
        "130,000,000 Berries"
    ),
    Character(
        8,
        "Franky",
        "Shipwright",
        R.drawable.franky,
        "O carpinteiro cibernético que construiu o Thousand Sunny.",
        "94,000,000 Berries"
    ),
    Character(
        9,
        "Brook",
        "Musician",
        R.drawable.brook,
        "O músico esqueleto e espadachim que voltou à vida com a Yomi Yomi no Mi.",
        "83,000,000 Berries"
    ),
    Character(
        10,
        "Jinbe",
        "Helmsman",
        R.drawable.jinbe,
        "O timoneiro da tripulação e um ex-Shichibukai, mestre do Karatê dos Homens-Peixe.",
        "438,000,000 Berries"
    )
)
