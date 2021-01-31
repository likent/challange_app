package com.mycompany.my.challenge.api

import com.mycompany.my.challenge.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {


    @GET("popular")
    fun getPopularMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<MovieResponse>

}