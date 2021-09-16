package com.codelabs.insplash.photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.RepositoryState
import com.codelabs.insplash.photos.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val repository: PhotoRepository
) : ViewModel() {

    private val _state = mutableStateOf<UiState<List<Photo>>>(UiState.Initial)
    val state: State<UiState<List<Photo>>> = _state

    init {
        getPhotos(1, 30)
    }

    fun getPhotos(page: Int, perPage: Int) {
        _state.value = UiState.Loading

        repository.getPhotos(page, perPage).onEach { state ->
            _state.value = when (state) {
                is RepositoryState.Success<*> ->
                    UiState.Success((state as RepositoryState.Success<List<Photo>>).value)
                is RepositoryState.Failure ->
                    UiState.Error(state.message)
            }
        }.launchIn(viewModelScope)
    }

}