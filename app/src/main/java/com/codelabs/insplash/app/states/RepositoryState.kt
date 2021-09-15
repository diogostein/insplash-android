package com.codelabs.insplash.app.states

sealed class RepositoryState<out T> {
    class Success<S>(val value: S) : RepositoryState<S>()
    class Failure(val message: String? = null) : RepositoryState<Nothing>()
}