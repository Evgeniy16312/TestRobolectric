package com.example.testrobolectric.repository

import com.example.testrobolectric.tests_search.model.SearchResponse
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface GitHubApi {

    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories")
    fun searchGithub(@Query("q") term: String?): Call<SearchResponse?>?

    //запрос с рх джава
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories")
    fun searchGithubRx(@Query("q") term: String?): Observable<SearchResponse>

    //запрос с корутинами
    @Headers("Accept: application/vnd.github.mercy-preview+json")
    @GET("search/repositories")
    fun searchGithubAsync(@Query("q") term: String?): Deferred<SearchResponse>
}