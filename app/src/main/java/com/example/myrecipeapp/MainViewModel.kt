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
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoriesState.value = _categoriesState.value.copy(
                    list = response.categories,
                    loading = false,
                    error = null
                )

            }
            catch(e:Exception){
                _categoriesState.value = _categoriesState.value.copy(
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