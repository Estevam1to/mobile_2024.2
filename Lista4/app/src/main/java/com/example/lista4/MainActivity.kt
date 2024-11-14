package com.example.lista4

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializando componentes do layout
        val profileImage = findViewById<ImageView>(R.id.profileImage)
        val nameText = findViewById<TextView>(R.id.nameText)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        val currentJobText = findViewById<TextView>(R.id.currentJobText)
        val experienceLayout = findViewById<LinearLayout>(R.id.experienceLayout)
        val skillsLayout = findViewById<LinearLayout>(R.id.skillsLayout)
        val educationLayout = findViewById<LinearLayout>(R.id.educationLayout)
        val certificationsLayout = findViewById<LinearLayout>(R.id.certificationsLayout)

        // Definindo informações de perfil
        nameText.text = "Ana Silva"
        descriptionText.text = "Desenvolvedora de software com 5 anos de experiência."
        currentJobText.text = "Emprego Atual: Engenheira de Software na TechX"

        // Lista de Experiências
        val experiencias = listOf(
            "Analista de Sistemas - Empresa A",
            "Desenvolvedora Júnior - Empresa B",
            "Estagiária - Empresa C"
        )

        // Adicionando experiências dinamicamente
        for (experiencia in experiencias) {
            val textView = TextView(this)
            textView.text = experiencia
            textView.textSize = 16f
            textView.setPadding(0, 8, 0, 8)
            experienceLayout.addView(textView)
        }

        // Lista de Habilidades
        val habilidades = listOf("Kotlin", "Java", "Android Development", "Git", "SQL")
        for (habilidade in habilidades) {
            val textView = TextView(this)
            textView.text = habilidade
            textView.textSize = 16f
            textView.setPadding(0, 8, 0, 8)
            skillsLayout.addView(textView)
        }

        // Lista de Educação
        val educacao = listOf(
            "Bacharelado em Ciência da Computação - Universidade X",
            "Curso de Desenvolvimento Android - Escola Y"
        )
        for (curso in educacao) {
            val textView = TextView(this)
            textView.text = curso
            textView.textSize = 16f
            textView.setPadding(0, 8, 0, 8)
            educationLayout.addView(textView)
        }

        // Lista de Certificações
        val certificacoes = listOf(
            "Certificação Android Developer - Google",
            "Certificação Kotlin - JetBrains"
        )
        for (certificacao in certificacoes) {
            val textView = TextView(this)
            textView.text = certificacao
            textView.textSize = 16f
            textView.setPadding(0, 8, 0, 8)
            certificationsLayout.addView(textView)
        }

        // Adicionando interatividade com Toast ao clicar na foto de perfil
        profileImage.setOnClickListener {
            Toast.makeText(this, "Foto de perfil de Ana Silva", Toast.LENGTH_SHORT).show()
        }
    }
}
