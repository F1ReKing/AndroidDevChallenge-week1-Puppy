package com.f1reking.androiddevchallenge.dogadoption.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.f1reking.androiddevchallenge.dogadoption.R
import com.f1reking.androiddevchallenge.dogadoption.data.Dog
import com.f1reking.androiddevchallenge.dogadoption.data.dogDataList
import com.f1reking.androiddevchallenge.dogadoption.navigation.Navigation
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.black
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.greyBg
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.textTitle
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun ListScreen(navController: NavController) {

    Surface(color = MaterialTheme.colors.background) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            TopAppBarComponent()
            LazyColumn(
                modifier = Modifier
                    .background(greyBg)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                items(dogDataList) { item ->
                    ItemLayout(item, navController)
                }
            }
        }
    }
}

@Composable
fun ItemLayout(dog: Dog, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    navController.navigate(Navigation.detailRoute(dog.id))
                }
            ), elevation = 4.dp

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp),
                shape = CircleShape,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
            ) {
                CoilImage(
                    data = dog.imgUrl,
                    contentDescription = "item_pic",
                    fadeIn = true,
                    contentScale = ContentScale.FillBounds
                ) {

                }
            }

            Column(verticalArrangement = Arrangement.Center) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = dog.name,
                        color = black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp, 5.dp)
                    )

                    val resId = if (dog.gender == 0) R.drawable.ic_male else R.drawable.ic_female

                    Image(
                        imageVector = ImageVector.vectorResource(id = resId),
                        contentDescription = "",
                        modifier = Modifier.size(20.dp)
                    )
                }

                Text(
                    text = dog.age,
                    color = textTitle,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 5.dp)
                )
            }

        }
    }
}

@Composable
fun TopAppBarComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Dog Adoption", color = black, fontSize = 20.sp)
    }
}

@Preview
@Composable
fun ListPreview() {
    ListScreen(rememberNavController())
}

