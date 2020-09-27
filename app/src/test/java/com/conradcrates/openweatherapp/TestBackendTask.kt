package com.conradcrates.openweatherapp

import com.conradcrates.openweatherapp.backend.BackendTask

class TestBackendTask<T>(private val result: T, task: (BackendTask<T>) -> Unit): BackendTask<T>(task) {

    private var success = true

    fun toSucceed() {
        success = true
    }

    fun toFail() {
        success = false
    }

    override fun addOnSuccessListener(onSuccessListener: (result: T) -> Unit): TestBackendTask<T> {
        super.addOnSuccessListener(onSuccessListener)
        if (success) {
            successResult(result)
        }
        return this
    }

    override fun addOnErrorListener(onErrorListener: (result: T?) -> Unit): TestBackendTask<T> {
        super.addOnErrorListener(onErrorListener)
        if (!success) {
            errorResult(result)
        }
        return this
    }
}