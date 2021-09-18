package com.codelabs.insplash.app

class Pager<T>(val pageSize: Int) {
    private var _pageNumber: Int = 1
    val pageNumber: Int get() = _pageNumber

    private var _hasReachedEndOfResults: Boolean = false
    val hasReachedEndOfResults: Boolean get() = _hasReachedEndOfResults

    private val _list = mutableListOf<T>()
    val list: List<T> = _list

    fun refresh(list: List<T>): Pager<T> {
        _hasReachedEndOfResults = list.isEmpty()

        if (!hasReachedEndOfResults) {
            _list.addAll(list)
            _pageNumber++
        }

        return this
    }

    fun reset() {
        _pageNumber = 1
        _list.clear()
        _hasReachedEndOfResults = false
    }
}