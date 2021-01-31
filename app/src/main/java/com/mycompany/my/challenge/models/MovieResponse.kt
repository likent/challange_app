package com.mycompany.my.challenge.models

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("results")
    val movieList: MutableList<Movie>
)