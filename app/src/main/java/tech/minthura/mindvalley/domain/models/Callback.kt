package tech.minthura.mindvalley.domain.models

interface Callback<T> {
    fun onSuccess(t : T)
    fun onError(error: Error)
}