package com.codelabs.insplash.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.RepositoryState
import com.codelabs.insplash.favorites.data.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _state = mutableStateOf<UiState<List<Photo>>>(UiState.Initial)
    val state: State<UiState<List<Photo>>> = _state

    init {
        getFavorites()
    }

    fun getFavorites() {
        _state.value = UiState.Loading

        repository.getFavorites().onEach { state ->
            _state.value = when (state) {
                is RepositoryState.Success<List<Photo>> -> UiState.Success(state.value)
                is RepositoryState.Failure -> UiState.Error(state.error.message)
            }
        }.launchIn(viewModelScope)
    }

}