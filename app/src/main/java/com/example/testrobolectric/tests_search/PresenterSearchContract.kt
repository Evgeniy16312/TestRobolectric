package com.example.testrobolectric.tests_search

import com.example.testrobolectric.presenter.PresenterContract

internal interface PresenterSearchContract : PresenterContract {

    fun searchGitHub(searchQuery: String)
}