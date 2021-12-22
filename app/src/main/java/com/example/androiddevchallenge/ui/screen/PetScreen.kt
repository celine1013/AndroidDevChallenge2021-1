package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.repos.Resource
import com.example.androiddevchallenge.ui.viewmodel.MainViewModel

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreviewPetScreen() {
    MyTheme {
        PetScreen(id = "1")
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreviewPetScreen() {
    MyTheme {
        PetScreen(id = "1")
    }
}


@Composable
fun PetScreen(id: String = "", viewModel: MainViewModel = viewModel()) {
    val animalResource by produceState<Resource<Animal>>(initialValue = Resource.Loading()){
        value = viewModel.getPetDetails(id)
    }

    if(animalResource is Resource.Success<*>) {
        val animal = (animalResource as Resource.Success<Animal>).data
        Column(
            modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.background)

        ) {
            Text(animal.name)
        }
    }else{
        Text("Loading")
    }

}
