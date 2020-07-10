package com.example.themoviedb.data.repository

import com.example.themoviedb.data.model.MovieList

interface MovieRepoInterface {

    suspend fun getMovieList(page: Int, genre: Int): MovieList?
}