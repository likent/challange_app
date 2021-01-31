package com.mycompany.my.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mycompany.my.challenge.datasource.MovieDataSource
import com.mycompany.my.challenge.factories.MovieDataSourceFactory
import com.mycompany.my.challenge.models.Movie

class MovieViewModel : ViewModel() {
    private var movieList : LiveData<PagedList<Movie>> = MutableLiveData()
    private var mutableLiveData = MutableLiveData<MovieDataSource>()
    private var loadingEvent: MutableLiveData<Boolean> = MutableLiveData()

    init {
        val factory: MovieDataSourceFactory by lazy {
            MovieDataSourceFactory {loadingEvent.postValue(it)}
        }
        mutableLiveData = factory.mutableLiveData
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()
        movieList = LivePagedListBuilder(factory, config).build()
    }

    fun getData() : LiveData<PagedList<Movie>>{
        return movieList
    }
    fun getLoadingEvent(): LiveData<Boolean>{
        return loadingEvent
    }
}
