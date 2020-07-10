package com.example.themoviedb.view.ulasan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themoviedb.R
import com.example.themoviedb.data.model.ResultXX
import com.example.themoviedb.data.model.ReviewsList
import com.example.themoviedb.helper.Helper
import com.example.themoviedb.helper.PaginationScrollListener
import com.example.themoviedb.recyclerview.Movie_Item
import com.example.themoviedb.recyclerview.Ulasan_Item
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_ulasan.*
import kotlinx.android.synthetic.main.loading_screen.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class UlasanFragment : Fragment() {

    private val vm: UlasanVM by inject()
    private val adapters = GroupAdapter<ViewHolder>()
    val data: MutableList<ResultXX> = mutableListOf()
    private var isLoadMore = false
    private var isLastPage = false
    var page = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ulasan, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapters.clear()

        val id = arguments?.getInt("id", 0)

        if (Helper.isOnline(requireContext())) {
            viewLifecycleOwner.lifecycleScope.launch {
                getVM(id!!, page)
            }
        } else {
            Helper.checkinternettoast(requireContext())
        }

        val layoutManagers = LinearLayoutManager(context)
        review.apply {
            layoutManager = layoutManagers
            adapter = adapters
        }

        review.addOnScrollListener(object : PaginationScrollListener(layoutManagers) {
            override fun isLastPage(): Boolean = isLastPage

            override fun isLoading(): Boolean = isLoadMore

            override fun loadMoreItems() {
                isLoadMore = true
                page++
                itemloading1.visibility = View.VISIBLE
                viewLifecycleOwner.lifecycleScope.launch {
                    getVM(id!!, page)
                }
            }

        })

    }

    suspend fun getVM(movieId: Int, page: Int) {
        vm.getUlasan(movieId, page)
        vm.ulasanData.observe(viewLifecycleOwner, Observer { ulasan ->
            loading.visibility = View.GONE


            if (ulasan?.results.isNullOrEmpty()) {
                isLastPage = true
                itemloading1.visibility = View.GONE
            } else {
                adapters.clear()
                if (isLoadMore) {
                    itemloading1.visibility = View.GONE
                    isLoadMore = false
                }

                data.addAll(ulasan.results)
                data.map {
                    adapters.add(Ulasan_Item(it, requireContext()))
                }
            }

        })
    }
}