package com.codelabs.insplash.app.states

import com.codelabs.insplash.app.ErrorType

sealed class RepositoryState<out T> {
    class Success<S>(val value: S) : RepositoryState<S>()
    open class Failure(val error: ErrorType = ErrorType.Unknown) : RepositoryState<Nothing>()
}