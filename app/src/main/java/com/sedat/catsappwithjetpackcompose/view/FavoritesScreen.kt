package com.sedat.catsappwithjetpackcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sedat.catsappwithjetpackcompose.R
import com.sedat.catsappwithjetpackcompose.lazycolumn.FavoritesLazyColumn
import com.sedat.catsappwithjetpackcompose.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    var loadOnce by remember {
        mutableStateOf(false)
    }

    if (!loadOnce){
        viewModel.getCatsFromRoom()
        loadOnce = true
    }

    val catList by remember {
        mutableStateOf(viewModel.catList)
    }

    Column(modifier = Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            Color(0xFF9E43C3),
                            Color(0xFF4D40BC),
                        )
                    )
                )
                .padding(6.dp)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.backspace_32),
                    contentDescription = "back",
                    modifier = Modifier
                        .padding(5.dp)
                        .clickable(
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                )
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = stringResource(id = R.string.favorites),
                    style = TextStyle(
                        color = Color(0xFFFFCC00),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.special_elite)),
                        letterSpacing = 1.sp
                    )
                )
            }
        }

        FavoritesLazyColumn(
            catList = catList.value,
            navController = navController)
    }
}