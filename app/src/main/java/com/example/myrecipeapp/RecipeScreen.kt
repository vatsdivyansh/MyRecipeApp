package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable


import androidx.compose.ui.layout.ContentScale

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier ,
                 viewstate:MainViewModel.RecipeState,
                 navigateToDetail : (Category) -> Unit){
    // We want to use our viewModel here becoz that will allows us to get data
    val recipeViewModel:MainViewModel = viewModel()
//    val viewState by recipeViewModel.categoriesState -> we are getting rid of viewState defined here and passing it inside RecipeScreen() . so now the recipe screen will nedd the viewState as well
    // the UI that I'm going to have is a box ->
    Box(modifier =Modifier.fillMaxSize() ){
        when{
           viewstate.loading -> {
             CircularProgressIndicator(progress = 0.89f, modifier.align(Alignment.Center))

          }
            viewstate.error!=null-> {
                Text("ERROR OCCURED")
            }
            else->{

                // display categories
                // when we are not loading and we also dont have error then we show list -->
                CategoryScreen(categories = viewstate.list , navigateToDetail)


            }
        }

    }
}
@Composable
fun CategoryScreen(categories:List<Category> , navigateToDetail : (Category) -> Unit){
    LazyVerticalGrid(GridCells.Fixed(2) , modifier = Modifier.fillMaxSize() ){
        items(categories){
            category ->
            CategoryItem(category = category ,  navigateToDetail)
        }

    }


}


@Composable
// how each item should look like--> // to allow the navigation we need to pass navigateToDetail inside CategoryItem() function
fun CategoryItem(category:Category ,
                 navigateToDetail : (Category) -> Unit
                 ){
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .clickable { navigateToDetail(category) }
        ,horizontalAlignment = Alignment.CenterHorizontally) {
        // this is how the Image of items looks like on UI->
        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop // optional, depending on your layout needs
        )



        // i want to have a text to be displayed along with the images of items. This is how the text with the image looks like->
        Text(

            text = category.strCategory , // strCategory i.e name of the category(or item) I want display as text

            color = Color.Black ,
            style = TextStyle(fontWeight = FontWeight.Bold) ,
            modifier = Modifier.padding(top = 4.dp)

        )


    }
}


