package com.example.themoviedb.view.detailmovie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.helper.Helper
import com.example.themoviedb.recyclerview.Trailer_Item
import com.example.themoviedb.view.movielist.MovieListVM
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class DetailMovieFragment : Fragment() {


    private val vm: DetailMovieVM by inject()
    val video = GroupAdapter<ViewHolder>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        video.clear()

        val id = arguments?.getInt("id", 0)
        Log.e("TAGO", id.toString())

        if (Helper.isOnline(requireContext())) {
            viewLifecycleOwner.lifecycleScope.launch {
                getVM(id!!)
            }
        } else {
            Helper.checkinternettoast(requireContext())
        }

        videoTriller.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = video
        }

        ulasanBtn.setOnClickListener {
            val bundle = bundleOf("id" to id)
            it.findNavController().navigate(
                R.id.action_detailMovieFragment_to_ulasanFragment
                , bundle
            )
        }

    }

    suspend fun getVM(movieId: Int) {
        vm.getDetailMovie(movieId)
        vm.detailMovie.observe(viewLifecycleOwner, Observer {
            Log.e("TAGO", it.title)
            titleMovie.text = it.title
            detailRating.rating = (it.vote_average - 5.0).toFloat()
            overviewContent.text = it.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w185//" + it.backdrop_path)
                .error(R.drawable.ic_baseline_local_movies_24)
                .into(poster1)
        })

        vm.getVideo(movieId)
        vm.movieData.observe(viewLifecycleOwner, Observer { it ->

            Log.e("TAGO", it.results[0].key)
            it.results.map { trailer ->
                video.add(Trailer_Item(trailer, requireContext()))
            }

        })

    }
}