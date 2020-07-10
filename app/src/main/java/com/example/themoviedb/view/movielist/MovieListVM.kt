package com.example.themoviedb.view.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.data.model.MovieList
import com.example.themoviedb.data.repository.MovieRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieListVM(val repo : MovieRepoInterface) : ViewModel() {

    val movieML = MutableLiveData<MovieList>()

    suspend fun getMoviestList(page:Int, genre:Int) = withContext(Dispatchers.IO){
        if (repo.getMovieList(page,genre)?.results.isNullOrEmpty()){
            movieML.postValue(null)
        }else {
            movieML.postValue(repo.getMovieList(page,genre))
        }

    }

    override fun onCleared() {
        super.onCleared()
    }
}