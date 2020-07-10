package com.example.themoviedb.view.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.data.model.GenreList
import com.example.themoviedb.data.repository.GenreRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GenreVm(val repo: GenreRepoInterface) : ViewModel() {


    val dataGenre = MutableLiveData<GenreList>()

    suspend fun getGenre(api_key: String, language: String) = withContext(Dispatchers.IO) {
        dataGenre.postValue(repo.getGenreList(api_key, language))
    }

    override fun onCleared() {
        super.onCleared()
    }
}