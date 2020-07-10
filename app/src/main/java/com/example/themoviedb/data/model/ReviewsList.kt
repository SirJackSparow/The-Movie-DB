package com.example.themoviedb.data.model

data class ReviewsList(
    val id: Int,
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)