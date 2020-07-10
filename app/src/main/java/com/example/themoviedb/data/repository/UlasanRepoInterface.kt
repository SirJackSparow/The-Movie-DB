package com.example.themoviedb.data.repository

import com.example.themoviedb.data.model.ReviewsList

interface UlasanRepoInterface {

   suspend fun getUlasan(movie_id:Int,page:Int) :  ReviewsList?
}