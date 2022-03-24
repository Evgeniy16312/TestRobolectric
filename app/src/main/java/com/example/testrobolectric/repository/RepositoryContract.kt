package com.example.testrobolectric.repository


interface RepositoryContract {

    fun searchGithub(query: String, callback: RepositoryCallback)
}