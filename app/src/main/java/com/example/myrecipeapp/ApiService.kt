package com.example.myrecipeapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
private val retrofit = Retrofit.Builder().baseUrl("www.themealdb.com/api/json/v1/1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build() // role of this code-> this code make a connection with the base URL in such a way that the data is readable as objects in Kotlin
     val recipeService = retrofit.create(ApiService::class.java) // here i am defining a service based on that which allows us to get "categories.php" which we have passed in @GET

interface ApiService{
    @GET("categories.php")// get helps us to make  http requests and we have to pass where this request should go to in our case this go to categories.php which comes from TheMealDB API
    // we will define the base of URL somewhere else
    //i.e List all meal categories
    //www.themealdb.com/api/json/v1/1/categories.php-> we are only using "categories.php" here inside @GETand will define rest of the URL somewhere else



    suspend fun getCategories():CategoriesResponse
    // role of suspend keyword here->Sure! The suspend keyword in suspend fun getCategories(): CategoriesResponse means that the function can pause its execution to wait for a long-running operation (like a network request) to complete, and then resume once it's done, all without blocking the thread. This ensures that the function can perform its task without freezing the app's user interface.
    // because tasks here arre asynchronous
    // suspend keyword says I will come back once I'm done executing than it runs on the background or wait for the response  so to speak




}