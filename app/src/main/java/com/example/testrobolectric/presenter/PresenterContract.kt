package com.example.testrobolectric.presenter

import com.example.testrobolectric.view.ViewContract


internal interface PresenterContract {

    fun onAttach(view: ViewContract) {}

    fun onDetach() {}
}