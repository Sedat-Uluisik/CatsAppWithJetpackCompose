package com.sedat.catsappwithjetpackcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sedat.catsappwithjetpackcompose.R
import com.sedat.catsappwithjetpackcompose.Util.Util.IMAGE_URL
import com.sedat.catsappwithjetpackcompose.model.CatItem
import com.sedat.catsappwithjetpackcompose.viewmodel.CatDetailsViewModel

@Composable
fun CatDetailsScreen(
    catItem: CatItem,
    navController: NavController,
    viewModel: CatDetailsViewModel = hiltViewModel()
) {

    var loadDataOnce by remember {
        mutableStateOf(false)
    }

    val isFavorite by remember {
        mutableStateOf(viewModel.isFavorite)
    }

    if(!loadDataOnce){
        viewModel.isFavorite(catItem.id)
        loadDataOnce = true
    }



    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Box(modifier = Modifier
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Image(
                        painter = painterResource(id = R.drawable.backspace_32),
                        contentDescription = "logo",
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable(
                                onClick = {
                                    navController.popBackStack("home_screen", false)
                                }
                            )
                    )

                    Icon(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    isFavorite.value = if(isFavorite.value){
                                        viewModel.deleteCatFromRoomWithId(catItem.id)
                                        false
                                    }else{
                                        viewModel.saveCatFromRoom(catItem)
                                        true
                                    }
                                }
                            ),
                        painter = painterResource(id = if(isFavorite.value) R.drawable.favorite_32 else R.drawable.favorite_border_32),
                        tint = Color(0xFFFFCC00),
                        contentDescription = "favorites")
                }
            }

            Column(
                modifier = Modifier
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = catItem.name.toString().uppercase(),
                    fontFamily = FontFamily(Font(R.font.organic_relief)),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(10.dp))

                Image(
                    painter = rememberImagePainter(data = "$IMAGE_URL/${catItem.reference_image_id}.jpg"),
                    contentDescription = "cat image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(start = 15.dp, end = 15.dp)
                )

                Spacer(modifier = Modifier.height(7.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = catItem.description.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontFamily = FontFamily.Monospace
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    Row {
                        Text(
                            text = stringResource(id = R.string.life_span),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                        Text(text = catItem.life_span.toString())
                    }

                    Spacer(modifier = Modifier.height(7.dp))

                    TextAndRating(text = stringResource(id = R.string.energy_level), rating = catItem.energy_level.toString().toInt())

                    Spacer(modifier = Modifier.height(7.dp))

                    TextAndRating(text = stringResource(id = R.string.grooming), rating = catItem.grooming.toString().toInt())

                    Spacer(modifier = Modifier.height(7.dp))

                    TextAndRating(text = stringResource(id = R.string.adaptability), rating = catItem.adaptability.toString().toInt())
                }
            }
        }
    }
}

@Composable
private fun TextAndRating(text: String, rating: Int) {
    Row {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(text = ": ")
        Row {
            for (i in 1..5){
                Icon(
                    painter = painterResource(id = R.drawable.star_24),
                    contentDescription = "",
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp),
                    tint = if(i <= rating) Color(0XFFFFCC00) else Color(0xFFA2ADB1)
                )
            }
        }
    }
}