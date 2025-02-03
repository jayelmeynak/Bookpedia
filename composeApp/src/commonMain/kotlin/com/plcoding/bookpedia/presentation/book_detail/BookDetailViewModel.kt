package com.plcoding.bookpedia.presentation.book_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BookDetailViewModel: ViewModel() {
    private val _state = MutableStateFlow(BookDetailState())
    val state = _state.asStateFlow()

    fun onAction(action: BookDetailAction) {
        when (action) {
            is BookDetailAction.OnBackClicked -> Unit

            is BookDetailAction.OnFavoriteClicked -> {
                _state.update {
                    it.copy(isFavorite = !it.isFavorite)
                }
            }

            is BookDetailAction.OnSelectedBookChanged -> {
                _state.update {
                    it.copy(book = action.book)
                }
            }
        }
    }
}