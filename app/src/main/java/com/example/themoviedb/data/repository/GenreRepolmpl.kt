package com.example.themoviedb.data.repository

import com.example.themoviedb.data.model.GenreList
import com.example.themoviedb.data.services.GlobalServices

class GenreRepolmpl(
    val service: GlobalServices

) : GenreRepoInterface {
    override suspend fun getGenreList(api_key: String, language: String): GenreList {
        return service.getGenreList(api_key, language).body()!!
    }
}