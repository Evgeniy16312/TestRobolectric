package com.example.testrobolectric.tests_details

import com.example.testrobolectric.view.ViewContract

internal interface ViewDetailsContract : ViewContract {

    fun setCount(count: Int)
}