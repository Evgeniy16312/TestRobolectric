package com.example.testrobolectric.view.search

import com.example.testrobolectric.model.SearchResult
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
