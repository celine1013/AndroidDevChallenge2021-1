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
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.androiddevchallenge.ui.components.BottomNavBar
import com.example.androiddevchallenge.ui.components.Screen
import com.example.androiddevchallenge.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

// Start building your app here!
@ExperimentalFoundationApi
@Composable
fun MyApp() {
    MyTheme {
        val allScreens = Screen.values().toList()
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = Screen.fromRoute(backstackEntry.value?.destination?.route)

        Scaffold(
            bottomBar = {
                BottomNavBar(
                    allScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name)
                    },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            MyNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.name,
        modifier = modifier
    ) {
        composable(Screen.Home.name) {
            MainScreen()
        }
        composable(Screen.Saved.name) {
            Text("Saved Screen")
        }
        composable(Screen.Mail.name) {
            Text("Mail Screen")
        }
        composable(Screen.Profile.name) {
            Text("Profile Screen")
        }
        val animalRoute = Screen.Animals.name
        composable(
            route = "${animalRoute}/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "rally://$animalRoute/{name}"
                }
            ),
        ) { entry ->
            val animalName = entry.arguments?.getString("name")
//            val account = UserData.getAccount(accountName)
//            SingleAccountBody(account = account)
        }
    }
}


