package com.example.themoviedb.recyclerview

import android.content.Context
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.themoviedb.R
import com.example.themoviedb.data.model.Genre
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_genre.view.*


class Genre_Item(val genre : Genre, val context: Context) : Item()  {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val genrename = viewHolder.itemView.category

        genrename.text = genre.name

        viewHolder.itemView.setOnClickListener {
            val bundle = bundleOf("id" to genre.id.toString())
            it.findNavController().navigate(R.id.action_genre_to_movieListFragment,bundle)
        }
    }

    override fun getLayout(): Int  = R.layout.item_genre
}