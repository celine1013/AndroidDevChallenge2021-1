package com.example.androiddevchallenge.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen(val icon: ImageVector?, val addToTab: Boolean = true) {
    Home(icon = Icons.Filled.Home),
    Saved(icon = Icons.Filled.Favorite),
    Mail(icon = Icons.Filled.Mail),
    Profile(icon = Icons.Filled.Person),
    Animals(icon = null, addToTab = false);

    companion object {
        fun fromRoute(route: String?): Screen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Saved.name -> Saved
                Mail.name -> Mail
                Profile.name -> Profile
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}