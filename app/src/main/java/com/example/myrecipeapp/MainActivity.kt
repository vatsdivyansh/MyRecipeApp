package com.example.myrecipeapp

import android.os.Bundle
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.myrecipeapp.ui.theme.MyRecipeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MyRecipeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // content which we can see on screen ->
                    // i.e we want our RecipeScreen composable to be shown on screen

//                 RecipeScreen() --> now add RecipeApp() here instead of RecipeScreen()
                    RecipeApp(navController = navController)
                }
            }
        }
    }
}
//if (BuildConfig.DEBUG) {
//    StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
//        .detectAll()
//        .penaltyLog()
//        .build())
//    StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
//        .detectAll()
//        .penaltyLog()
//        .build())
//}

