package com.example.androiddevchallenge.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.viewmodel.MainViewModel

@ExperimentalFoundationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        HomeScreen()
    }
}

@ExperimentalFoundationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme {
        HomeScreen()
    }
}

@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)

    ) {
        Row(modifier = Modifier.background(color = MaterialTheme.colors.primary)) {
            TopSearchBar()
        }
        Adoption(
            viewMore = {navController?.navigate(Screen.Animals.name + "/cat")},
            showAnimalDetails = {animal ->  navController?.navigate(Screen.Pet.name + "/${animal.id}")}
        )
        MoreAnimals(
            viewMore = {navController?.navigate(Screen.Animals.name + "/all")},
            showAnimalList = {animal ->  navController?.navigate(Screen.Animals.name + "/${animal.species}")}
        )
    }
}

@Composable
fun TopSearchBar() {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = { text = it },
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search animal",
                tint = MaterialTheme.colors.primary
            )
        },
        label = { Text("Search for your future pet", color = MaterialTheme.colors.primary) },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(28.dp),
    )
}

@ExperimentalFoundationApi
@Composable
fun Adoption(viewMore: () -> Unit = {}, showAnimalDetails: (Animal)->Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text("Adopt a Cat", style = MaterialTheme.typography.h5)
        }
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { viewMore() },
            ) {
                Text("View More", textAlign = TextAlign.End)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "more")
            }
        }
    }
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
    ) {
        AnimalList(
            animalList = Animal.getDummyAnimals(),
            showDetails = showAnimalDetails
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun MoreAnimals(viewMore: () -> Unit, showAnimalList: (Animal)->Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp, vertical = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text("Other Animals", style = MaterialTheme.typography.h5)
        }
        Column(horizontalAlignment = Alignment.End, modifier = Modifier.fillMaxWidth()) {
            TextButton(
                onClick = { viewMore() },
            ) {
                Text("View More", textAlign = TextAlign.End)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "more")
            }
        }
    }
    Row(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth()
    ) {
        AnimalList(
            animalList = Animal.getDummyAnimals(),
            showDetails = showAnimalList
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun AnimalList(animalList: List<Animal>, displayNum: Int = 3,  showDetails: (Animal)->Unit) {
    val scrollState = rememberLazyListState()
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        state = scrollState,
        content = {
            items(displayNum) { index ->
                AnimalCard(animal = animalList[index], showDetails)
            }
        },
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    )
}

@Composable
fun AnimalCard(animal: Animal, showDetails: (Animal)->Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = { /* Ignoring onClick */ })
            .padding(horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(130.dp)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = 2.dp
        ) {
            val photoResource = if (animal.photo.isNotEmpty()) animal.photo.first()
            else R.drawable.no_image
            Image(
                painterResource(photoResource),
                contentDescription = "animal photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(animal.name, style = MaterialTheme.typography.subtitle1)
        Text("See Details",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(2.dp)
                .clickable { showDetails(animal)}
        )
    }
}
