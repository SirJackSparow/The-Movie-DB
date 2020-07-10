package com.example.themoviedb.view.genre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.data.services.Harcode
import com.example.themoviedb.helper.Helper
import com.example.themoviedb.recyclerview.Genre_Item
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_genre.*
import kotlinx.android.synthetic.main.loading_screen.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class GenreListfragment : Fragment() {
    private val vm: GenreVm by inject()
    private val genreAdapter = GroupAdapter<ViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_genre, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
         genreAdapter.clear()
        if (Helper.isOnline(this.requireContext())) {
            viewLifecycleOwner.lifecycleScope.launch {
                getVM()
            }
        } else {
            Helper.checkinternettoast(requireContext())
        }


        genres.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = genreAdapter
        }
    }

    suspend fun getVM() {
        vm.getGenre(Harcode.api_key, Harcode.laguage)
        vm.dataGenre.observe(viewLifecycleOwner, Observer {
            Log.e("TAG", it.genres.size.toString())


            loading.visibility = View.GONE
            it.genres.map { genre ->
                genreAdapter.add(Genre_Item(genre, this.requireContext()))

            }


        })

    }
}