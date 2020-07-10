package com.example.themoviedb.view.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.data.model.MovieList
import com.example.themoviedb.data.model.Result
import com.example.themoviedb.helper.Helper
import com.example.themoviedb.helper.PaginationScrollListener
import com.example.themoviedb.recyclerview.Movie_Item
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.item_loadmore_loading.*
import kotlinx.android.synthetic.main.loading_screen.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class MovieListFragment : Fragment() {

    private val vm: MovieListVM by inject()
    val movieAdapter = GroupAdapter<ViewHolder>()

    private var isLoadMore = false
    private var isLastPage = false
    var page = 1

    var data = mutableListOf<Result>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieAdapter.clear()
        data.clear()

        var id = arguments?.getString("id")

        if (Helper.isOnline(requireContext())) {

            viewLifecycleOwner.lifecycleScope.launch {
                getVm(page, id!!.toInt())
            }
        } else {
            Helper.checkinternettoast(requireContext())
        }

        val layoutmanager = GridLayoutManager(context, 2)

        movieList.apply {
            layoutManager = layoutmanager
            adapter = movieAdapter
        }

        movieList.addOnScrollListener(object : PaginationScrollListener(layoutmanager) {
            override fun isLastPage(): Boolean = isLastPage

            override fun isLoading(): Boolean = isLoadMore

            override fun loadMoreItems() {
                isLoadMore = true
                page++
                itemloading.visibility = View.VISIBLE
                viewLifecycleOwner.lifecycleScope.launch {
                    getVm(page, id!!.toInt())
                }
            }

        })
    }

    suspend fun getVm(page: Int, genre: Int) {
        vm.getMoviestList(page, genre)
        vm.movieML.observe(viewLifecycleOwner, Observer { movie ->
            loading.visibility = View.GONE

            if (movie?.results.isNullOrEmpty()) {
                isLastPage = true
                itemloading.visibility = View.GONE
            } else {
                movieAdapter.clear()
                if (isLoadMore) {
                    itemloading.visibility = View.GONE
                    isLoadMore = false
                }

                data.addAll(movie.results)
                data.map {
                    movieAdapter.add(Movie_Item(it, requireContext()))
                }

            }

        })
    }
}