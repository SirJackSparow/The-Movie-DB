package com.example.themoviedb.data.services

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.example.themoviedb.data.model.GenreList
import com.example.themoviedb.data.model.MovieList
import com.example.themoviedb.data.model.ReviewsList
import com.example.themoviedb.data.model.TraillerList
import com.example.themoviedb.data.model.detailmovie.DetailMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface GlobalServices {

    @GET("genre/movie/list")
    suspend fun getGenreList(
        @Query("api_key") api_key: String,
        @Query("language") language: String

    ): Response<GenreList>

    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("with_genres") genre: Int
    ): Response<MovieList>

    @GET("movie/{movie_id}")
    suspend fun detailMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String

    ): Response<DetailMovie>

    @GET("movie/{movie_id}/reviews")
    suspend fun getReviewUser(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<ReviewsList>

    @GET("movie/{movie_id}/videos")
    suspend fun getTraillerMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Query("language") language: String

    ): Response<TraillerList>

}

object Harcode {
    val api_key: String = "989a063fed83e060d0faebef7e8f2f38"
    val laguage: String = "en-US"
}