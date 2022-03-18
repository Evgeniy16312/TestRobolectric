package com.example.testrobolectric.presenter.search

import com.example.testrobolectric.presenter.PresenterContract
import com.example.testrobolectric.view.search.ViewSearchContract


internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
    fun onAttach(view : ViewSearchContract)
    fun onDetach()
    fun isPresenterAttached() : Boolean
}
