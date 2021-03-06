package com.codelabs.insplash.photos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelabs.insplash.app.Const
import com.codelabs.insplash.app.ErrorType
import com.codelabs.insplash.app.Pager
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

    private val _pager = Pager<Photo>(Const.GridViewPaging.PAGE_SIZE)

    private val _query = mutableStateOf<String?>("")
    val query: State<String?> = _query

    init {
        getPhotos(reload = true)
    }

    fun getPhotos(reload: Boolean = false, query: String? = null) {
        _query.value = query

        if (reload) _pager.reset()

        _state.value = if (reload) UiState.Loading else UiState.PaginationLoading(_pager.list)

        val result = if (query == null || query.isBlank()) {
            repository.getPhotos(_pager.pageNumber, _pager.pageSize)
        } else {
            repository.searchPhotos(query, _pager.pageNumber, _pager.pageSize)
        }

        result.onEach { state ->
            _state.value = when (state) {
                is RepositoryState.Success<List<Photo>> -> getSuccessState(state)
                is RepositoryState.Failure ->
                    if (reload)
                        UiState.Error(state.error.message)
                    else
                        UiState.PaginationError(_pager.list, state.error.message)
            }
        }.launchIn(viewModelScope)
    }

    private fun getSuccessState(state: RepositoryState.Success<List<Photo>>): UiState<List<Photo>> {
        // We finish pagination on page N to save API rate limit
        val shouldFinishPagination = _pager.pageNumber >= 3

        return if (_pager.refresh(state.value).hasReachedEndOfResults || shouldFinishPagination) {
            UiState.PaginationFinished(_pager.list)
        } else {
            UiState.Success(_pager.list)
        }
    }

}