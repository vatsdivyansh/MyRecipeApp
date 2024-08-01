// Main ViewModel takes care of taking the information from the data side to the UI side
package com.example.myrecipeapp

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    // create a global variable for our MainViewModel
    // we are going to create our own state so to speak
    private val  _categoriesState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoriesState // public variable that is exposed to other classes e.g main activity(categoriesState is a read only property which is of type State<RecipeState> which is initialized  with the value of _categoriesState. This is typically used in a state management context within a Kotlin Android application, where State represents an observable state holder.)
   // (viewMidel takes care of the connection bw UI and data)

    // on initialisation->
    // so the moment my viewModel is opened I want to run the  fetchCategories function i.e the coroutine that we described below that calls a  function that is declared using suspend key gets the categories from the internet using retrofit
    init{
        fetchCategories()
    }


    private fun fetchCategories(){
        // fetchCategories is a function  that we need to call when we want to display anything
        // launching the coroutines that allows us to call functions that are suspend function i.e defined using suspend keyword i.e the fucntions that dont happens synchronouslhy but happens in the backgroung

        viewModelScope.launch {
            // now we will use try and catch becoz something may go wrong e.g no internet connection etc etc..
            try {
                // if something goes wrong we dont want to execute this  try block  code

                val response = recipeService.getCategories() // inorder to get something from internet we need to put that in suspend function. This line calls the getCategories function from the recipeService and stores it in the response . here this function is likely to make a network call to fetch categories from the internet
                _categoriesState.value = _categoriesState.value.copy( // updates the value of  _categoriesState by copying the current state and modyfying soecific fields
                     list = response.categories, // set the 'list' field of the new state by copying the values obtained from the 'response'
                    loading = false, // set the loading field  of the new state to false indicating that the data has finished loading
                    error = null // set the error filed of the we state to null indicating that there are no errors
                )

            }
            catch(e:Exception){
                // when we get an error

                _categoriesState.value = _categoriesState.value.copy(
                    // when we get an error we say are not not loading anymore but we do have an error ->
                    loading = false ,
                    error = "Error fetching categories ${e.message}"
                )

            }
        }
    }

    data class RecipeState(
            val loading:Boolean = true  ,
            val list:List<Category> = emptyList() ,// lets define it to be an empty list at the start
            val error:String? = null // error may not be there so at that point I am going to error is null so to speak (if we want a variable to be able to null we should use this syntax)


            )
}