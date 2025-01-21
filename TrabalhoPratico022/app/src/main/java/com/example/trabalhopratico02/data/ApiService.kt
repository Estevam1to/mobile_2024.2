package com.example.trabalhopratico02.data

import com.example.trabalhopratico02.data.models.CreatePostRequest
import com.example.trabalhopratico02.data.models.CreateUserRequest
import com.example.trabalhopratico02.data.models.Post
import com.example.trabalhopratico02.data.models.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @POST("users/")
    suspend fun createUser(@Body user: CreateUserRequest): User

    @GET("users/")
    suspend fun getUsers(): List<User>

    @POST("users/{user_id}/posts/")
    suspend fun createPost(
        @Path("user_id") userId: Int,
        @Body post: CreatePostRequest
    ): Post

    @GET("users/{user_id}/posts/")
    suspend fun getPosts(@Path("user_id") userId: Int): List<Post>

    @PUT("posts/{post_id}/")
    suspend fun updatePost(
        @Path("post_id") postId: Int,
        @Body post: CreatePostRequest
    ): Post

    @DELETE("posts/{post_id}/")
    suspend fun deletePost(
        @Path("post_id") postId: Int,
    ): Post
}