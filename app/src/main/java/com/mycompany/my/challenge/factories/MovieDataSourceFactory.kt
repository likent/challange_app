package com.mycompany.my.challenge.factories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mycompany.my.challenge.datasource.MovieDataSource
import com.mycompany.my.challenge.models.Movie

class MovieDataSourceFactory(private val loadCallback: (Boolean) -> Unit): DataSource.Factory<Int, Movie>() {

    val mutableLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(loadCallback)
        mutableLiveData.postValue(movieDataSource)
        return movieDataSource
    }
}