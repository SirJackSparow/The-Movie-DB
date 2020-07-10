package com.example.themoviedb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.themoviedb.data.model.GenreList
import com.example.themoviedb.data.model.MovieList
import com.example.themoviedb.data.services.Harcode
import com.example.themoviedb.di.appModule
import com.example.themoviedb.di.myAppModule
import com.example.themoviedb.view.detailmovie.DetailMovieVM
import com.example.themoviedb.view.genre.GenreVm
import com.example.themoviedb.view.movielist.MovieListVM
import com.example.themoviedb.view.ulasan.UlasanVM
import kotlinx.coroutines.runBlocking
import org.junit.*

import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ViewModelTest : KoinTest {

    val genrevm: GenreVm by inject()

    lateinit var api_key: String
    lateinit var language: String
    lateinit var api_kkey2:String
    lateinit var language2:String

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observerData: Observer<GenreList>


    @Before
    fun before() {
        api_key = Harcode.api_key
        language = Harcode.laguage
        api_kkey2 = "fhgsdjhg"
        language2 ="fdfdf"
        MockitoAnnotations.initMocks(this)
        startKoin { modules(myAppModule) }
    }

    @After
    fun after() {
        stopKoin()
    }

    //positive case
    @Test
    fun apiPositive() = runBlocking {

        genrevm.dataGenre.observeForever(observerData)
        genrevm.getGenre(api_key,language)

       Assert.assertNotNull(genrevm.dataGenre.value)
    }


    //negative case
    @Test
    fun apiNegative() = runBlocking {
        genrevm.dataGenre.observeForever(observerData)
        genrevm.getGenre(api_kkey2,language2)
        Assert.assertNotNull(genrevm.dataGenre.value)
    }

}