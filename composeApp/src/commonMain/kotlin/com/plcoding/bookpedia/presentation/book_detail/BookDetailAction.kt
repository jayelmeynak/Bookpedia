package com.plcoding.bookpedia.presentation.book_detail

import com.plcoding.bookpedia.domain.Book

sealed interface BookDetailAction {
    data object OnBackClicked : BookDetailAction
    data object OnFavoriteClicked : BookDetailAction
    data class OnSelectedBookChanged(val book: Book) : BookDetailAction
}