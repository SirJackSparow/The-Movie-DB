package com.example.themoviedb.recyclerview

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.example.themoviedb.R
import com.example.themoviedb.data.model.ResultX
import com.example.themoviedb.helper.Helper
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailLoader.OnThumbnailLoadedListener
import com.google.android.youtube.player.YouTubeThumbnailView
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_youtube.view.*


class Trailer_Item(val result: ResultX, val context: Context) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val youtube = viewHolder.itemView.youtube
        val progress = viewHolder.itemView.progress
        val text = viewHolder.itemView.kosong
        val playlist = viewHolder.itemView.playlist
        youtube.initialize(Helper.API_KEY, object : YouTubeThumbnailView.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubeThumbnailView?,
                p1: YouTubeThumbnailLoader?
            ) {
                p1?.setVideo(result.key)

                p1?.setOnThumbnailLoadedListener(object :
                    OnThumbnailLoadedListener {
                    override fun onThumbnailLoaded(
                        youTubeThumbnailView: YouTubeThumbnailView,
                        s: String
                    ) {
                        progress.visibility = View.GONE
                        playlist.visibility = View.VISIBLE
                        p1.release()
                    }

                    override fun onThumbnailError(
                        youTubeThumbnailView: YouTubeThumbnailView,
                        errorReason: YouTubeThumbnailLoader.ErrorReason
                    ) {
                        progress.visibility = View.GONE
                        text.visibility = View.VISIBLE
                    }
                })
            }


            override fun onInitializationFailure(
                p0: YouTubeThumbnailView?,
                p1: YouTubeInitializationResult?
            ) {
                playlist.visibility = View.VISIBLE
                p0?.setBackgroundResource(R.drawable.ic_launcher_background)
            }

        })

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=${result.key}")
            )

            intent.putExtra("force_fullscreen", true)
            intent.putExtra("finish_on_ended", true)
            context.startActivity(intent)
        }


    }

    override fun getLayout(): Int = R.layout.item_youtube
}