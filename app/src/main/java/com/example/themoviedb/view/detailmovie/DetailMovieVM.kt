package com.example.themoviedb.view.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.data.model.TraillerList
import com.example.themoviedb.data.model.detailmovie.DetailMovie
import com.example.themoviedb.data.repository.DetailRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailMovieVM(val repo: DetailRepoInterface) : ViewModel() {

    val detailMovie = MutableLiveData<DetailMovie>()
    val movieData = MutableLiveData<TraillerList>()

    suspend fun getDetailMovie(movieId: Int) = withContext(Dispatchers.IO) {
        if (repo.getDetail(movieId) == null) {
            detailMovie.postValue(null)
        } else {
            detailMovie.postValue(repo.getDetail(movieId))
        }
    }

    suspend fun getVideo(movieId: Int) = withContext(Dispatchers.IO) {
        if (repo.getTrailer(movieId)?.results.isNullOrEmpty()) {
            movieData.postValue(null)
        } else {
            movieData.postValue(repo.getTrailer(movieId))
        }
    }

}