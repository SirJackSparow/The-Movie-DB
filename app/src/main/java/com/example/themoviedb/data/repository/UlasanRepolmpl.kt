package com.example.themoviedb.data.repository

import com.example.themoviedb.data.model.ReviewsList
import com.example.themoviedb.data.services.GlobalServices
import com.example.themoviedb.data.services.Harcode

class UlasanRepolmpl(
    val services: GlobalServices
) : UlasanRepoInterface {
    override suspend fun getUlasan(movie_id:Int,page:Int): ReviewsList? {
        return if (services.getReviewUser(movie_id,Harcode.api_key,Harcode.laguage,page).isSuccessful){
            services.getReviewUser(movie_id,Harcode.api_key,Harcode.laguage,page).body()
        }else{
            null
        }
    }

}