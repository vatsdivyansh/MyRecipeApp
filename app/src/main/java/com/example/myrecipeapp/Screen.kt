package com.example.myrecipeapp


sealed class Screen(val route:String) {
    //The sealed keyword in Kotlin is used to declare a sealed class, which helps create a restricted class hierarchy. This ensures that all subclasses of the sealed class are known at compile-time and must be defined in the same file as the sealed class.
    // this makes sure we don't run into problems where e.g we accidently write the route name incorrectly when we want to call the route or pass the route because I want to have all the routes here

    // this class will take care of individual routes
    object RecipeScreen: Screen("recipescreen")
    object DetailScreen:Screen("detailscreen") // this is very good practice to make sure that we're creating constant variables for each of those screens that we have
    // we can use these objects to  determine the right location or what is our route
}