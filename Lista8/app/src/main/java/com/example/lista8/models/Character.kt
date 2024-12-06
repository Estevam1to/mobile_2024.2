package com.example.lista8.models

import com.example.lista8.R

data class Character(
    val id: Int,
    val name: String,
    val role: String,
    val imageRes: Int,
    val description: String,
    val bounty: String,
    var isFavorite: Boolean = false
)

val characterList = mutableListOf(
    Character(
        id = 1,
        name = "Monkey D. Luffy",
        role = "Captain",
        imageRes = R.drawable.luffy,
        description = "O protagonista e capitão dos Chapéus de Palha. Seu sonho é se tornar o Rei dos Piratas.",
        bounty = "1,500,000,000 Berries"
    ),
    Character(
        id = 2,
        name = "Roronoa Zoro",
        role = "Swordsman",
        imageRes = R.drawable.zoro,
        description = "Espadachim habilidoso e braço direito de Luffy. Ele usa o estilo de luta Santoryu, com três espadas.",
        bounty = "320,000,000 Berries"
    ),
    Character(
        id = 3,
        name = "Nami",
        role = "Navigator",
        imageRes = R.drawable.nami,
        description = "A navegadora da tripulação, especialista em mapas e meteorologia. Sonha em mapear o mundo.",
        bounty = "66,000,000 Berries"
    ),
    Character(
        id = 4,
        name = "Usopp",
        role = "Sniper",
        imageRes = R.drawable.usopp,
        description = "O franco-atirador e contador de histórias. Ele é conhecido por sua criatividade em batalha.",
        bounty = "200,000,000 Berries"
    ),
    Character(
        id = 5,
        name = "Sanji",
        role = "Chef",
        imageRes = R.drawable.sanji,
        description = "O cozinheiro da tripulação. Sanji também é um mestre em chutes, com seu estilo de luta Black Leg.",
        bounty = "330,000,000 Berries"
    ),
    Character(
        id = 6,
        name = "Tony Tony Chopper",
        role = "Doctor",
        imageRes = R.drawable.chopper,
        description = "O médico da tripulação e uma rena que comeu a Hito Hito no Mi. Ele pode se transformar em formas diferentes.",
        bounty = "100 Berries"
    ),
    Character(
        id = 7,
        name = "Nico Robin",
        role = "Archaeologist",
        imageRes = R.drawable.robin,
        description = "Uma arqueóloga habilidosa e especialista em Poneglyphs. Ela é a única sobrevivente de Ohara.",
        bounty = "130,000,000 Berries"
    ),
    Character(
        id = 8,
        name = "Franky",
        role = "Shipwright",
        imageRes = R.drawable.franky,
        description = "O carpinteiro cibernético da tripulação, responsável por construir o navio Thousand Sunny.",
        bounty = "94,000,000 Berries"
    ),
    Character(
        id = 9,
        name = "Brook",
        role = "Musician",
        imageRes = R.drawable.brook,
        description = "O músico esqueleto da tripulação. Ele voltou à vida graças à Yomi Yomi no Mi.",
        bounty = "83,000,000 Berries"
    ),
    Character(
        id = 10,
        name = "Jinbe",
        role = "Helmsman",
        imageRes = R.drawable.jinbe,
        description = "O timoneiro da tripulação e um ex-Shichibukai. É um mestre no Karatê dos Homens-Peixe.",
        bounty = "438,000,000 Berries"
    )
)
