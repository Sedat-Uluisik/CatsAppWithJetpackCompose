package com.sedat.catsappwithjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sedat.catsappwithjetpackcompose.model.CatItem
import com.sedat.catsappwithjetpackcompose.ui.theme.CatsAppWithJetpackComposeTheme
import com.sedat.catsappwithjetpackcompose.view.CatDetailsScreen
import com.sedat.catsappwithjetpackcompose.view.FavoritesScreen
import com.sedat.catsappwithjetpackcompose.view.HomeScreen
import com.sedat.catsappwithjetpackcompose.view.SplashScreen
import com.sedat.catsappwithjetpackcompose.viewmodel.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatsAppWithJetpackComposeTheme {
                /*Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                }*/
                NavHost()
            }
        }
    }
}

@Composable
fun NavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("home_screen"){
            HomeScreen(navController = navController)
        }
        composable("cat_details_screen"){
            val catItem = navController.previousBackStackEntry?.savedStateHandle?.get<CatItem>("catItem")

            catItem?.let {
                CatDetailsScreen(it, navController)
            }
        }
        composable("favorites_screen"){
            FavoritesScreen(navController)
        }
    }
}
