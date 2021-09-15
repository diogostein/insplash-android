package com.codelabs.insplash.app.states

sealed class UiState<out T> {
    object Initial : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    class Error(val message: String? = null) : UiState<Nothing>()
    class Success<C>(val value: C) : UiState<C>()
    object PaginationLoading : UiState<Nothing>()
    class PaginationError(val message: String) : UiState<Nothing>()
    object PaginationFinished : UiState<Nothing>()
}