package com.codelabs.insplash.app.states

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    class Error(val message: String? = null) : UiState<Nothing>()
    open class Success<V>(val value: V) : UiState<V>()

    open class Pagination<V>(value: V) : Success<V>(value)
    class PaginationLoading<V>(value: V) : Pagination<V>(value)
    class PaginationError<V>(value: V, val message: String? = null) : Pagination<V>(value)
    class PaginationFinished<V>(value: V) : Pagination<V>(value)
}