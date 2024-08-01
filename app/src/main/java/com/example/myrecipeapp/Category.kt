package com.example.myrecipeapp

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import kotlinx.parcelize.Parcelize

// Day 9 Lec:138 Setting up our categories and data class

// below data class is developed on the Basis of Json object
// after adding the new plugin in the build.gradle.kts (:app) make changes here also -->

@Parcelize
data class Category(
    val idCategory:String,
    val strCategory :String,
    val strCategoryThumb:String ,
    val strCategoryDescription:String
):Parcelable



// base on below JSON structure we designed our above data class i.e Category

// Json object representing a category of food from a meal or recipe database ->
// "idCategory": "1",
//            "strCategory": "Beef",
//            "strCategoryThumb": "https://www.themealdb.com/images/category/beef.png",
//            "strCategoryDescription": "Beef is the culinary name for meat from cattle, particularly skeletal muscle. Humans have been eating beef since prehistoric times.[1] Beef is a source of high-quality protein and essential nutrients.[2]"


// but what we actually have is a list of individual Category items -->
data class CategoriesResponse(val categories:List<Category>) //  it defines a kotlin class that have only a single property i.e categories:A list of Category objects, where "Category" is a type of another data class that defines the structure of each categr=ory object within the list
// structure of Json object here --> { "categories" :[ {} ,{} .....,{} ] } --> visit: https://www.themealdb.com/api/json/v1/1/categories.php

