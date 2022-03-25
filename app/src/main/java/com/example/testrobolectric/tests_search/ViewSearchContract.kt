package com.example.testrobolectric.tests_search

import com.example.mockito.tests_search.model.SearchResult
import com.example.testrobolectric.view.ViewContract

internal interface ViewSearchContract : ViewContract {

    fun displaySearchResults(
        searchResults: List<SearchResult>,
        totalCount: Int
    )

    fun displayError()
    fun displayError(error: String)
    fun displayLoading(show: Boolean)
}