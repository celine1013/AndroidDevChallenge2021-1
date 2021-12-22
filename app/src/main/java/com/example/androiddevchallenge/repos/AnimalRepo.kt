package com.example.androiddevchallenge.repos

import android.content.Context
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalRepo(context: Context) {

    suspend fun getAnimal(id: String): Resource<Animal> = withContext(Dispatchers.IO){
        return@withContext try{
            val animal = animals.find { it.id == id } ?: throw Exception("No animal found")
            Resource.Success(animal)
        } catch (e: Exception){
            Resource.Failure(e)
        }
    }

    companion object{
        private val animals = listOf<Animal>(
            Animal(id = "1", name = "Tobby", species = "Cat", age = 1.5, photo = mutableListOf(R.drawable.cat_1)),
            Animal(id = "2", name = "Misty", species = "Cat", age = 3.0, photo = mutableListOf(R.drawable.cat_2)),
            Animal(id = "3", name = "Ollie", species = "Cat", age = 1.0, photo = mutableListOf(R.drawable.cat_3)),
            Animal(id = "4", name = "Pixie", species = "Cat", age = 5.0, photo = mutableListOf(R.drawable.cat_4)),
        )

        @Volatile
        private var instance: AnimalRepo? = null

        @JvmStatic
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: AnimalRepo(context.applicationContext).also { instance = it }
            }
    }
}