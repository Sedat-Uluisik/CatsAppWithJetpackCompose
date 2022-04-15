package com.sedat.catsappwithjetpackcompose.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sedat.catsappwithjetpackcompose.HomeLazyColumn
import com.sedat.catsappwithjetpackcompose.viewmodel.HomeScreenViewModel
import com.sedat.catsappwithjetpackcompose.R
import com.sedat.catsappwithjetpackcompose.model.CatItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalUnitApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val scope = rememberCoroutineScope()
    var job: Job? by remember {
        mutableStateOf(null)
    }

    var loadOnce by remember {
        mutableStateOf(false)
    }

    val catList by remember {
        mutableStateOf(viewModel.catList)
    }

    if(!loadOnce){
        viewModel.getCatsFromApi()
        loadOnce = true
    }

    if(catList.value.isNotEmpty()){

        Surface(Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFF9E43C3),
                                Color(0xFF4D40BC)
                            )
                        )
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(15.dp),
                            painter = painterResource(id = R.drawable.cat_32),
                            contentDescription = "logo"
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text = stringResource(id = R.string.cats),
                            style = TextStyle(
                                color = Color(0xFFFFCC00),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.special_elite)),
                                letterSpacing = 1.sp
                            )
                        )
                    }
                    Icon(
                        modifier = Modifier
                            .clickable(
                                onClick = {
                                    navController.navigate("favorites_screen")
                                }
                            ),
                        painter = painterResource(id = R.drawable.favorite_32),
                        tint = Color(0xFFFFCC00),
                        contentDescription = "favorites")
                }

                SearchBar(){ text, clear->
                    job?.cancel()
                    job = scope.launch {
                        delay(500)
                        if(text.isNotEmpty() && !clear)
                            viewModel.search(text)
                        else{
                            job?.cancel()
                            if(text.isEmpty() && clear)
                                viewModel.getCatsFromApi()
                        }
                    }
                }

                if(catList.value.isNotEmpty())
                    ObserveDataAndShow(navController, catList.value)
            }
        }
    }
}

@Composable
fun ObserveDataAndShow(
    navController: NavController,
    catListFromApi: List<CatItem>
) {
    HomeLazyColumn(
        catList = catListFromApi,
        navController = navController
    )
}

@Composable
fun SearchBar(
    onSearch: (String, Boolean) -> Unit
) {

    var tex by remember {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color(0XFFFFCC00),
                shape = RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp),
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = tex,
            onValueChange = {
                tex = it
                onSearch(it, false)
            },
            label = {
                Text(text = stringResource(id = R.string.search), color = Color(0XFF9E43C3))
            },
            leadingIcon = {
                  if(tex.isNotEmpty()){
                      Image(
                          painter = painterResource(id = R.drawable.clear_32),
                          contentDescription = "clear",
                          modifier = Modifier.clickable(
                              onClick = {
                                  tex = ""
                              }
                          )
                      )
                  }
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(
                color = Color(0xFF9E43C3),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier
                .weight(1F)
                .background(Color.Transparent)
                .border(BorderStroke(2.dp, Color(0xFF9E43C3)), shape = RoundedCornerShape(7.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0XFFFFCC00)
            )
        )

        Box(
            modifier = Modifier
                .padding(5.dp)
                .clickable(
                    onClick = {
                        onSearch("", true)
                    }
                )
        ){
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(0xFF9E43C3),
                                Color(0xFF4D40BC)
                            )
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )

            ){
                Image(
                    painter = painterResource(id = R.drawable.search_48),
                    contentDescription = "search",
                    modifier = Modifier
                        .padding(7.dp)
                )
            }
        }
    }
}