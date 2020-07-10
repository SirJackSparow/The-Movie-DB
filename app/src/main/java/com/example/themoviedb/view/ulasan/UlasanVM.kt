package com.example.themoviedb.view.ulasan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.data.model.ReviewsList
import com.example.themoviedb.data.repository.UlasanRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UlasanVM(val repo : UlasanRepoInterface) : ViewModel() {

    val ulasanData = MutableLiveData<ReviewsList>()

    suspend fun getUlasan(movieId:Int,page:Int) = withContext(Dispatchers.IO){
        if (repo.getUlasan(movieId,page)?.results.isNullOrEmpty()){
          ulasanData.postValue(null)
        }else {
            ulasanData.postValue(repo.getUlasan(movieId,page))
        }
    }
}