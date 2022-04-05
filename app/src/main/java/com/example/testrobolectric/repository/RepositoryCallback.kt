package com.example.testrobolectric.repository

import com.example.testrobolectric.tests_search.model.SearchResponse
import retrofit2.Response

interface RepositoryCallback {

    fun handleGitHubResponse(response: Response<SearchResponse?>?)
    fun handleGitHubError()
}