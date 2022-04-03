package com.example.testrobolectric.repository

import com.example.testrobolectric.tests_search.model.SearchResponse
import io.reactivex.Observable


interface RepositoryContract {

    fun searchGithub(query: String, callback: RepositoryCallback)

    fun searchGithub(query: String): Observable<SearchResponse>
}