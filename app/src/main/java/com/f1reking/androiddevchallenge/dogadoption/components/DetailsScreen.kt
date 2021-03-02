package com.f1reking.androiddevchallenge.dogadoption.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.f1reking.androiddevchallenge.dogadoption.R
import com.f1reking.androiddevchallenge.dogadoption.data.Dog
import com.f1reking.androiddevchallenge.dogadoption.data.dogDataList
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.black
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.purple500
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.textDesc
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.textSubTitle
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
    val context = LocalContext.current
    Column {

        Row {

            Column {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = dog.name,
                        color = black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
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
                    color = textSubTitle,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                )
            }

            Button(
                onClick = {
                    Toast.makeText(context, "${dog.name} is yours now!", Toast.LENGTH_SHORT).show()
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = purple500,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().padding(10.dp,10.dp)

            ) {
                Text(text = "Adopt", style = MaterialTheme.typography.button)
            }
        }



        Text(
            text = dog.description,
            color = textDesc,
            fontSize = 14.sp,
            modifier = Modifier.padding(10.dp, 20.dp)
        )
    }
}

@Preview
@Composable
private fun DetailsPreview() {
    DetailsScreen(rememberNavController(), 1)
}
