package com.conradcrates.openweatherapp.backend

open class BackendTask<T>(task: (BackendTask<T>) -> Unit) {

    init {
        task.invoke(this)
    }

    private val successListeners = ArrayList<(T) -> Unit>()
    private val errorListeners = ArrayList<(T) -> Unit>()

    fun successResult(result: T){
        for(listener in successListeners){
            listener.invoke(result)
        }
    }

    fun errorResult(result: T){
        for (listener in errorListeners){
            listener.invoke(result)
        }
    }

    open fun addOnSuccessListener(onSuccessListener: (result: T) -> Unit): BackendTask<T> {
        successListeners.add(onSuccessListener)
        return this
    }

    open fun addOnErrorListener(onErrorListener: (result: T) -> Unit): BackendTask<T> {
        errorListeners.add(onErrorListener)
        return this
    }
}