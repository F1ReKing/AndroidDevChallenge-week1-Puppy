/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.f1reking.androiddevchallenge.dogadoption

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.f1reking.androiddevchallenge.dogadoption.components.DetailsScreen
import com.f1reking.androiddevchallenge.dogadoption.components.ListScreen
import com.f1reking.androiddevchallenge.dogadoption.navigation.Navigation
import com.f1reking.androiddevchallenge.dogadoption.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme(darkTheme = false) {
                ProvideWindowInsets {
                    MyApp()
                }
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    NavGraph()
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Navigation.NAV_LIST_SCREEN) {
        composable(route = Navigation.NAV_LIST_SCREEN) {
            ListScreen(navController = navController)
        }

        composable(route = Navigation.NAV_DETAILS_SCREEN,
            arguments = listOf(
                navArgument(Navigation.NAV_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            DetailsScreen(navController = navController, it.getIdArgument(Navigation.NAV_ID))
        }
    }
}

fun NavBackStackEntry.getIdArgument(key: String) =
    arguments?.getInt(key) ?: error("$key not found")

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
