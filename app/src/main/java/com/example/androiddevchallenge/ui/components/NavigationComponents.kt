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

package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navDeepLink
import com.example.androiddevchallenge.ui.screen.HomeScreen
import com.example.androiddevchallenge.ui.screen.PetScreen
import com.example.androiddevchallenge.ui.screen.Screen

@ExperimentalFoundationApi
@Composable
fun MyNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        //bottom nav bar
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Saved.route) {
            Text("Saved Screen")
        }
        composable(Screen.Mail.route) {
            Text("Mail Screen")
        }
        composable(Screen.Profile.route) {
            Text("Profile Screen")
        }

        addPetGraph(navController)
    }
}

private fun NavGraphBuilder.addPetGraph(navController: NavController) {
    navigation(route = Screen.Pet.route, startDestination = PetScreen.PetSpecies.route) {
        composable(PetScreen.PetSpecies.route) {
            Text("Pets all species")
        }
        composable(PetScreen.PetDetails.route) { entry ->
            val petId = entry.arguments?.getString("petId")
            requireNotNull(petId) { "petId parameter wasn't found. Please make sure it's set!" }
            Text("Pet: $petId")
        }
        composable(PetScreen.PetList.route) { entry ->
            val species = entry.arguments?.getString("species")
            requireNotNull(species) { "species parameter wasn't found. Please make sure it's set!" }
            Text("Category: $species")
        }
    }
}

@Composable
fun BottomNavBar(
    allScreens: List<Screen>,
    onTabSelected: (Screen) -> Unit,
    currentRoute: String?
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        allScreens.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { Icon(screen.icon, contentDescription = screen.route) } },
                label = { Text(text = screen.route) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == screen.route,
                onClick = {
                    onTabSelected(screen)
                }
            )
        }
    }

}