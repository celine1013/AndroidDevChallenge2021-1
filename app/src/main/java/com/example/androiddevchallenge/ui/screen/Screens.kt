package com.example.androiddevchallenge.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val icon: ImageVector?) {
    object Home: Screen(route = "home", icon = Icons.Filled.Home)
    object Saved: Screen(route= "saved", icon = Icons.Filled.Favorite)
    object Mail: Screen(route="mail", icon = Icons.Filled.Mail)
    object Profile: Screen(route="profile", icon = Icons.Filled.Person)
    object Pet: Screen(route="pet", icon = null) //all animal categories

}

sealed class PetScreen(val route: String, val icon: ImageVector?){
    object PetSpecies: Screen(route="pet/all", icon = null)

    object PetDetails: Screen(route="pet/{petId}", icon = null){
        fun createRoute(petId: String) = "pet/$petId"
    }

    object PetList: Screen(route="pet/{species}", icon = null){
        fun createRoute(species: String) = "pet/$species"
    }
}