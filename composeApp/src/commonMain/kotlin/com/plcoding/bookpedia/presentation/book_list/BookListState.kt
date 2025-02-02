package com.plcoding.bookpedia.presentation.book_list

import com.plcoding.bookpedia.core.presentation.UiText
import com.plcoding.bookpedia.domain.Book

data class BookListState(
    val searchQuery: String = "",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading:Boolean = false,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,

    )
