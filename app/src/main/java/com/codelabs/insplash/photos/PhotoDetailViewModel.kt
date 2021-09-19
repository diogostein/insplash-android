package com.codelabs.insplash.photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.RepositoryState
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.photos.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _state = mutableStateOf<UiState<Photo>>(UiState.Initial)
    val state: State<UiState<Photo>> = _state

    fun getPhoto(id: String) {
        _state.value = UiState.Loading

        repository.getPhoto(id).onEach { state ->
            delay(3000)
            _state.value = when (state) {
                is RepositoryState.Success<Photo> -> UiState.Success(state.value)
                is RepositoryState.Failure -> UiState.Error(state.message)
            }
        }.launchIn(viewModelScope)
    }

}