package com.example.androiddevchallenge.model

import com.example.androiddevchallenge.R

data class Animal(
    var name: String = "",
    var species: String = "",
    var age: Double = 0.0,
    var tags: MutableList<String> = mutableListOf(),
    var status: String = "",
    var sex: Int = 0,
    var description: String = "",
    var photo: MutableList<Int> = mutableListOf()

){

    companion object{
        fun getDummyAnimals() = listOf<Animal>(
            Animal(name = "Tobby", species = "Cat", age = 1.5, photo = mutableListOf(R.drawable.cat_1)),
            Animal(name = "Misty", species = "Cat", age = 3.0, photo = mutableListOf(R.drawable.cat_2)),
            Animal(name = "Ollie", species = "Cat", age = 1.0, photo = mutableListOf(R.drawable.cat_3)),
            Animal(name = "Pixie", species = "Cat", age = 5.0, photo = mutableListOf(R.drawable.cat_4)),
        )
    }
}
