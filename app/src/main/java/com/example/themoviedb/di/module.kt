package com.example.themoviedb.di


import com.example.themoviedb.data.repository.*
import com.example.themoviedb.data.services.GlobalServices
import com.example.themoviedb.view.detailmovie.DetailMovieVM
import com.example.themoviedb.view.genre.GenreVm
import com.example.themoviedb.view.movielist.MovieListVM
import com.example.themoviedb.view.ulasan.UlasanVM
import com.google.gson.GsonBuilder
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { createWebService<GlobalServices>() }
}


val dataModule = module {

    //genre
    single { GenreRepolmpl(get()) as GenreRepoInterface }
    viewModel { GenreVm(get()) }
    //movie
    single { MovieRepolmpl(get()) as MovieRepoInterface }
    viewModel { MovieListVM(get()) }
    // detail
    single { DetailRepolmpl(get()) as DetailRepoInterface }
    viewModel { DetailMovieVM(get()) }

    //ulasan
    single { UlasanRepolmpl(get()) as UlasanRepoInterface }
    viewModel { UlasanVM(get()) }


}

inline fun <reified T> createWebService(): T {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd HH:mm:ss")
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

val myAppModule = listOf(appModule, dataModule)

