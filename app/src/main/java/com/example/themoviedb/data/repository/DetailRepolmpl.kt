package com.example.themoviedb.data.repository

import com.example.themoviedb.data.model.TraillerList
import com.example.themoviedb.data.model.detailmovie.DetailMovie
import com.example.themoviedb.data.services.GlobalServices
import com.example.themoviedb.data.services.Harcode

class DetailRepolmpl(
    val services: GlobalServices
) : DetailRepoInterface {
    override suspend fun getDetail(movie_id: Int): DetailMovie? {
        return if (services.detailMovie(movie_id,Harcode.api_key, Harcode.laguage).isSuccessful) {
            services.detailMovie(movie_id,Harcode.api_key, Harcode.laguage).body()
        } else {
            null
        }
    }

    override suspend fun getTrailer(movie_id: Int): TraillerList? {
        return if (services.getTraillerMovie(
                movie_id,
                Harcode.api_key,
                Harcode.laguage
            ).isSuccessful
        ) {
            services.getTraillerMovie( movie_id,Harcode.api_key, Harcode.laguage).body()
        } else {
            null
        }
    }
}