package com.example.themoviedb.data.repository

import com.example.themoviedb.data.model.GenreList

interface GenreRepoInterface {

  suspend  fun getGenreList(api_key: String, language: String) : GenreList
}