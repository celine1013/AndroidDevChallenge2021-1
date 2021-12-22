package com.example.androiddevchallenge.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.produceState
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.*
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Animal
import com.example.androiddevchallenge.repos.AnimalRepo
import com.example.androiddevchallenge.repos.Resource

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val animapRepo = AnimalRepo.getInstance(application)

    suspend fun getPetDetails(id: String) = animapRepo.getAnimal(id)
}