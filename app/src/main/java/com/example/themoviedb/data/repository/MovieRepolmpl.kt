package com.example.themoviedb.data.repository


import com.example.themoviedb.data.model.MovieList
import com.example.themoviedb.data.services.GlobalServices
import com.example.themoviedb.data.services.Harcode

class MovieRepolmpl(
    val services: GlobalServices
) : MovieRepoInterface {
    override suspend fun getMovieList(page: Int, genre: Int): MovieList? {
        return if (services.getMovieList(
                Harcode.api_key,
                Harcode.laguage,
                page,
                genre
            ).isSuccessful
        ) {
            services.getMovieList(Harcode.api_key, Harcode.laguage, page, genre).body()
        } else {
            null
        }
    }
}