package com.f1reking.androiddevchallenge.dogadoption.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.f1reking.androiddevchallenge.dogadoption.data.Dog
import com.f1reking.androiddevchallenge.dogadoption.data.dogDataList
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.black
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.textTitle
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun DetailsScreen(navController: NavController, id: Int) {
    Column {

        val dog = dogDataList.filter { dog -> dog.id == id }.getOrNull(0)

        if (dog != null) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                DogDetailHeader(dog, navController)
                DogInfo(dog)
            }

        } else {
            Text(text = "not found.")
        }
    }
}

@Composable
private fun DogDetailHeader(dog: Dog, navController: NavController) {
    Surface(modifier = Modifier.fillMaxWidth()) {
        CoilImage(
            data = dog.imgUrl,
            contentDescription = "item_pic",
            fadeIn = true,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.height(400.dp)
        ) {

        }

        Box(modifier = Modifier.statusBarsPadding()) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "back",
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .size(40.dp)
                    .padding(8.dp),
                tint = MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
private fun DogInfo(dog: Dog) {
    Column {

        Text(
            text = dog.name,
            color = black,
            fontSize = 18.sp,
            modifier = Modifier.padding(10.dp)
        )

        Text(
            text = dog.age,
            color = textTitle,
            fontSize = 14.sp,
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}

@Preview
@Composable
private fun DetailsPreview() {
    DetailsScreen(rememberNavController(),1)
}
