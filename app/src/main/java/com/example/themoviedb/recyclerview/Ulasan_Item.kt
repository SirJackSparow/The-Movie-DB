package com.example.themoviedb.recyclerview

import android.content.Context
import com.example.themoviedb.R
import com.example.themoviedb.data.model.ResultXX
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_ulasan.view.*

class Ulasan_Item(val resul:ResultXX, context: Context) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
       val author = viewHolder.itemView.author
       val conten = viewHolder.itemView.conten

        author.text = resul.author
        conten.text = resul.content
    }

    override fun getLayout(): Int  = R.layout.item_ulasan
}