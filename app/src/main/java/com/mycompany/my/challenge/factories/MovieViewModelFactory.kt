package com.mycompany.my.challenge.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mycompany.my.challenge.viewmodels.MovieViewModel
import javax.inject.Inject

class MovieViewModelFactory @Inject constructor() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if(modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel() as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}