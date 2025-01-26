package com.example.msgapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.msgapp2.data.local.database.AppDataBase
import com.example.msgapp2.repository.MessageRepository
import com.example.msgapp2.ui.theme.MsgApp2Theme
import com.example.msgapp2.ui.view.MessageApp
import com.example.msgapp2.viewModel.MessageViewModel
import com.example.msgapp2.viewModel.MessageViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "messages-db"
        ).fallbackToDestructiveMigration().build()

        val repository = MessageRepository(db.messageDao())
        enableEdgeToEdge()
        setContent {
            MsgApp2Theme {
                val viewModel: MessageViewModel =
                    viewModel(factory = MessageViewModelFactory(repository))
                MessageApp(viewModel)
            }
        }
    }
}