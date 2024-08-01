package com.example.myrecipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController: NavHostController){
    val recipeViewModel:MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        // nav host controller will take care of two compoables one for recipe screen and one for detail screen (i.e bw which two screens we want navigation to occur)
        composable(route = Screen.RecipeScreen.route){
            RecipeScreen(viewstate = viewstate, navigateToDetail ={
                navController.currentBackStackEntry?.savedStateHandle?.set("cat" , it) // it is responsible for passing data from current screen to the detail screen , it utilizes the savedStatweHandle which is a component of the Compose navigation framework OR we can say that here we are storing the category and 
                navController.navigate(Screen.DetailScreen.route)  // this line enables us to navigate to DetailScreen
                // serialization --> here we have passed entire object "it" and in order to send objects from one screen to another we need to serialize them i.e we need to make them parcels or parcelable thats why in Category.kt we say that parcelize the class object and make it parcelable so that we can serialize them and deserialize them  and what serialize does is it makes an object into an string e.g object of class Category has various fiels e.g idCategory and when deserializing that is reverted back to object
                // why object of class Category need to be serialized is because its data type dont just have a single string or any other type but a collection of many
                // so now it allows us to store an entire objeect of type Category type inside of our save state handle

            } )
        }
        composable(route = Screen.DetailScreen.route){
            // here I'm going to use BackStackEntry to get the category whose details I just viewed on DetailScreen(we're saying that the category that we want to get from our savedStateHandle) -->


            val category = navController.previousBackStackEntry?.savedStateHandle?.get<Category>("cat ")?: Category("" , "" , "" , "")
             // now pass this category to CategoryDetailScreen which is our which is our main composable 
            CategoryDetailScreen(category = category)
        }


    }


}