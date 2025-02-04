package com.plcoding.bookpedia.presentation.book_list

import com.plcoding.bookpedia.core.presentation.UiText
import com.plcoding.bookpedia.domain.Book

data class BookListState(
    val searchQuery: String = "kotlin",
    val searchResults: List<Book> = emptyList(),
    val favoriteBooks: List<Book> = emptyList(),
    val isLoading:Boolean = true,
    val selectedTabIndex: Int = 0,
    val errorMessage: UiText? = null,

    )
