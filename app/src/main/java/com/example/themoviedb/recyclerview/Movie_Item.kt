package com.example.themoviedb.recyclerview

import android.content.Context
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.data.model.Result
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_movielist.view.*

class Movie_Item(val result: Result, context: Context) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val poster = viewHolder.itemView.poster
        val title = viewHolder.itemView.title
        val rating = viewHolder.itemView.rating

        Picasso.get().load("https://image.tmdb.org/t/p/w185//" + result.poster_path)
            .error(R.drawable.ic_baseline_local_movies_24)
            .into(poster)
        title.text = result.title
        rating.rating = (result.vote_average - 5.0).toFloat()

        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf("id" to result.id)
            it.findNavController()
                .navigate(R.id.action_movieListFragment_to_detailMovieFragment, bundle)
        }


    }

    override fun getLayout(): Int = R.layout.item_movielist
}