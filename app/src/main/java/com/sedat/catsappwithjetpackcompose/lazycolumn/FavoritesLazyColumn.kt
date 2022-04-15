package com.sedat.catsappwithjetpackcompose.lazycolumn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.sedat.catsappwithjetpackcompose.R
import com.sedat.catsappwithjetpackcompose.Util.Util
import com.sedat.catsappwithjetpackcompose.model.CatItem
import com.sedat.catsappwithjetpackcompose.viewmodel.FavoritesViewModel

@Composable
fun FavoritesLazyColumn(
    catList: List<CatItem>,
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFFFFCC00))
    ){
        items(catList){cat ->
            LazyColumnRow(
                catItem = cat
            ){catItem, favBtnClick->
                if (favBtnClick){
                    viewModel.deleteCatFromRoomWithId(catItem.id)
                }else{
                    navController.currentBackStackEntry?.savedStateHandle?.set("catItem", catItem)
                    navController.navigate("cat_details_screen")
                }
            }
        }
    }
}

@Composable
private fun LazyColumnRow(catItem: CatItem, click: (CatItem, Boolean) -> Unit) {

    var favIcon by remember {
        mutableStateOf(R.drawable.favorite_32)
    }

    Box(modifier = Modifier
        .padding(3.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(0xFF9E43C3),
                        Color(0xFF4D40BC)
                    )
                ),
                RoundedCornerShape(10.dp)
            )
            .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .weight(1F)
                    .clickable(
                        onClick = {
                            click(catItem, false)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter(data = "${Util.IMAGE_URL}/${catItem.reference_image_id}.jpg"),
                    contentDescription = "",
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .padding(5.dp),
                    contentScale = ContentScale.Inside
                )
                Text(
                    text = catItem.name.toString(),
                    style = TextStyle(
                        color = Color(0xFFFFCC00),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.patrick_hand)),
                        letterSpacing = 1.sp
                    )
                )
            }

            Image(
                painter = painterResource(id = favIcon),
                contentDescription = "favorite",
                modifier = Modifier
                    .clickable(
                        onClick = {
                            favIcon = R.drawable.favorite_border_32
                            click(catItem, true)
                        }
                    )
            )
        }
    }
}