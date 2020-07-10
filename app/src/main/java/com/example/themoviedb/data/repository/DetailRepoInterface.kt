package com.example.themoviedb.data.repository

import com.example.themoviedb.data.model.TraillerList
import com.example.themoviedb.data.model.detailmovie.DetailMovie

interface DetailRepoInterface {
  suspend  fun getDetail(movie_id:Int) : DetailMovie?

  suspend fun getTrailer(movie_id:Int) : TraillerList?
}