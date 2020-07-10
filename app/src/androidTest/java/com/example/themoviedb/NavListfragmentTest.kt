package com.example.themoviedb

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.themoviedb.view.SplashScreen
import com.example.themoviedb.view.detailmovie.DetailMovieFragment
import com.example.themoviedb.view.genre.GenreListfragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4ClassRunner::class)
class NavListfragmentTest {

    val mockNavController = mock(NavController::class.java)

    @Test
    fun testNavFragmentPositiveShowRecyclerGenre() {
        val titleScenario = launchFragmentInContainer<GenreListfragment>()
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        onView(withId(R.id.genres)).check(matches(isDisplayed()))
    }


    @Test
    fun testNavFragmentNegativeShowRecyclerGenre() {
        val mockNavController = mock(NavController::class.java)
        val titleScenario = launchFragmentInContainer<SplashScreen>()
        titleScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), mockNavController)
        }
        onView(withId(R.id.genres)).check(matches(isDisplayed()))
    }



}