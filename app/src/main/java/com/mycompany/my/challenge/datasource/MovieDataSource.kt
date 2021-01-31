package com.mycompany.my.challenge.datasource

import androidx.paging.PageKeyedDataSource
import com.mycompany.my.challenge.BuildConfig
import com.mycompany.my.challenge.api.RetrofitClient
import com.mycompany.my.challenge.models.Movie
import com.mycompany.my.challenge.models.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDataSource (loadCallback: (Boolean) -> Unit) : PageKeyedDataSource <Int,Movie>() {

    private var moviesList: MutableList<Movie> = mutableListOf()
    private val load = loadCallback


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        load.invoke(true)
        RetrofitClient().apiService.getPopularMovies(BuildConfig.API_KEY_v3, 1).enqueue(object: Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                load.invoke(false)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    callback.onResult(it.movieList, 1, 2)
                    moviesList.addAll(it.movieList)
                }
                load.invoke(false)
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        RetrofitClient().apiService.getPopularMovies(BuildConfig.API_KEY_v3, params.key).enqueue(object: Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                load.invoke(false)
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                response.body()?.let {
                    callback.onResult(it.movieList, params.key+1)
                    moviesList.addAll(it.movieList)
                }
                load.invoke(false)
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
    }

}