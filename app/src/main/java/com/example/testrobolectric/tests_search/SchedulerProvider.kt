package com.example.testrobolectric.tests_search

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun ui(): Scheduler
    fun io(): Scheduler
}