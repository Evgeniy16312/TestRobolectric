package com.example.testrobolectric.tests_details

import com.example.testrobolectric.presenter.PresenterContract

internal interface PresenterDetailsContract : PresenterContract {

    fun setCounter(count: Int)
    fun onIncrement()
    fun onDecrement()
}