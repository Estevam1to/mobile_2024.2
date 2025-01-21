package com.example.trabalhopratico02.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trabalhopratico02.data.RetrofitInstance
import com.example.trabalhopratico02.data.models.CreatePostRequest
import com.example.trabalhopratico02.data.models.CreateUserRequest
import com.example.trabalhopratico02.data.models.Post
import com.example.trabalhopratico02.data.models.User
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    var posts: List<Post> by mutableStateOf(listOf())
    var users: List<User> by mutableStateOf(listOf())

    private val userId = 1

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                users = RetrofitInstance.api.getUsers()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchPosts() {
        viewModelScope.launch {
            try {
                posts = RetrofitInstance.api.getPosts(userId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createUser(
        name: String,
        email: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val newUser = CreateUserRequest(name, email)
                RetrofitInstance.api.createUser(newUser)
                fetchUsers()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun createPost(
        title: String,
        content: String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val newPost = CreatePostRequest(title, content)
                RetrofitInstance.api.createPost(userId, newPost)
                fetchPosts()
                onSuccess()
            } catch (e: Exception) {
                e.printStackTrace()
                onError()
            }
        }
    }

    fun deletePost(postId: Int) {
        viewModelScope.launch {
            try {
                RetrofitInstance.api.deletePost(postId)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updatePost(
        postId: Int,
        title: String,
        content: String,
    ) {
        viewModelScope.launch {
            try {
                val updatedPost = CreatePostRequest(title, content)
                RetrofitInstance.api.updatePost(postId, updatedPost)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}