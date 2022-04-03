package com.example.testrobolectric.tests_search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testrobolectric.repository.GitHubApi
import com.example.testrobolectric.repository.GitHubRepository
import com.example.testrobolectric.repository.RepositoryContract
import com.example.testrobolectric.tests_search.MainActivity.Companion.BASE_URL
import com.example.testrobolectric.tests_search.SchedulerProvider
import com.example.testrobolectric.tests_search.SearchSchedulerProvider
import com.example.testrobolectric.tests_search.model.SearchResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel(
    private val repository: RepositoryContract = GitHubRepository(
        Retrofit.Builder()
            .baseUrl(BASE_URL)
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GitHubApi::class.java)
    ),
    private val appSchedulerProvider: SchedulerProvider = SearchSchedulerProvider()
) : ViewModel() {
    private val _liveData = MutableLiveData<ScreenState>()
    private val liveData: LiveData<ScreenState> = _liveData
    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    fun subscribeToLiveData() = liveData

    fun searchGitHub(searchQuery: String) {
        _liveData.value = ScreenState.Loading
        viewModelCoroutineScope.launch {
            val searchResponse = repository.searchGithubAsync(searchQuery)
            val searchResults = searchResponse.searchResults
            val totalCount = searchResponse.totalCount
            if (searchResults != null && totalCount != null) {
                _liveData.value = ScreenState.Working(searchResponse)
            } else {
                _liveData.value =
                    ScreenState.Error(Throwable("Search results or total count are null"))

            }
        }
    }

    private fun handleError(error: Throwable) {
        _liveData.value =
            ScreenState.Error(
                Throwable(
                    error.message ?: "Response is null or unsuccessful"
                )
            )
    }

    override fun onCleared() {
        super.onCleared()
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }
}

//    fun searchGitHub(searchQuery: String) {
//        //Dispose
//        val compositeDisposable = CompositeDisposable()
//        compositeDisposable.add(
//            repository.searchGithub(searchQuery)
//                .subscribeOn(appSchedulerProvider.io())
//                .observeOn(appSchedulerProvider.ui())
//                .doOnSubscribe { _liveData.value = ScreenState.Loading }
//                .subscribeWith(object : DisposableObserver<SearchResponse>() {
//                    override fun onNext(searchResponse: SearchResponse) {
//                        val searchResults = searchResponse.searchResults
//                        val totalCount = searchResponse.totalCount
//                        if (searchResults != null && totalCount != null) {
//                            _liveData.value = ScreenState.Working(searchResponse)
//                        } else {
//                            _liveData.value =
//                                ScreenState.Error(Throwable("Search results or total count are null"))
//                        }
//                    }
//
//                    override fun onError(e: Throwable) {
//                        _liveData.value =
//                            ScreenState.Error(
//                                Throwable(
//                                    e.message ?: "Response is null or unsuccessful"
//                                )
//                            )
//                    }
//
//                    override fun onComplete() {}
//                }
//                )
//        )
//    }


sealed class ScreenState {
    object Loading : ScreenState()
    data class Working(val searchResponse: SearchResponse) : ScreenState()
    data class Error(val error: Throwable) : ScreenState()
}