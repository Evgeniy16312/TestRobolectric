package com.example.testrobolectric.repository

import com.example.mockito.tests_search.model.SearchResponse
import retrofit2.Response

class FakeGitHubRepository : RepositoryContract {

    override fun searchGithub(
        query: String,
        callback: RepositoryCallback
    ) {
        callback.handleGitHubResponse(Response.success(SearchResponse(42, listOf())))
    }
}