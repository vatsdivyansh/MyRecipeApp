package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

// adding CategoryDetailScreen in our recipe app -->
@Composable
fun CategoryDetailScreen(category: Category){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = category.strCategory , textAlign = TextAlign.Center)
        Image(
            painter = rememberAsyncImagePainter(model = category.strCategoryThumb),
            contentDescription = "${category.strCategory} Thumbnail",
            modifier = Modifier
                .wrapContentSize()
                .aspectRatio(1f),
//            contentScale = ContentScale.Crop --> optional, depending on your layout needs
        )
        Text(text = category.strCategoryDescription ,
            textAlign = TextAlign.Justify ,
            modifier =  Modifier.verticalScroll(rememberScrollState()) , // this basically allows us to scroll through the screen when there is a lot of text to be displayed on the screen.While scrolling the above text and image will not go anywhere but this text only get scrolled
            )





    }
}