package com.sedat.catsappwithjetpackcompose.view

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.sedat.catsappwithjetpackcompose.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(key1 = true) {
        delay(1500L)
        navController.popBackStack()
        navController.navigate("home_screen")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xFF4D40BC),
                            Color(0xFF9E43C3)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.cat_256),
                    contentDescription = "",
                    modifier = Modifier
                        .defaultMinSize(256.dp)
                )

                Spacer(modifier = Modifier.height(25.dp))

                Text(
                    text = stringResource(id = R.string.cats),
                    fontFamily = FontFamily(Font(R.font.special_elite)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp,
                    letterSpacing = 1.sp,
                    color = Color(0XFFFFCC00)
                )
            }
        }
    }
}