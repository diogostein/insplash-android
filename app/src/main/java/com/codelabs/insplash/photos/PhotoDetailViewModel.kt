package com.codelabs.insplash.photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.insplash.app.models.Photo
import com.codelabs.insplash.app.states.RepositoryState
import com.codelabs.insplash.app.states.UiState
import com.codelabs.insplash.favorites.data.FavoriteRepository
import com.codelabs.insplash.photos.data.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val favoriteRepository: FavoriteRepository,
) : ViewModel() {

    private val _state = mutableStateOf<UiState<Photo>>(UiState.Initial)
    val state: State<UiState<Photo>> = _state

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite

    fun getPhoto(id: String) {
        _state.value = UiState.Loading

        photoRepository.getPhoto(id).onEach { state ->
            _state.value = when (state) {
                is RepositoryState.Success<Photo> -> {
                    _isFavorite.value = state.value.isFavorite ?: false
                    UiState.Success(state.value)
                }
                is RepositoryState.Failure -> UiState.Error(state.error.message)
            }
        }.launchIn(viewModelScope)
    }

    fun setFavorite(photoId: String, favoriteChecked: Boolean) {
        _isFavorite.value = !favoriteChecked

        val result = if (favoriteChecked)
            favoriteRepository.removeFavorite(photoId)
        else
            favoriteRepository.saveFavorite(photoId)

        result.onEach { state ->
            if (state is RepositoryState.Failure) {
                _isFavorite.value = favoriteChecked
            }
        }.launchIn(viewModelScope)
    }

}