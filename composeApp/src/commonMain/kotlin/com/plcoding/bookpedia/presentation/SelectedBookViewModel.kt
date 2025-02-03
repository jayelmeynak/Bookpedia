package com.plcoding.bookpedia.presentation

import androidx.lifecycle.ViewModel
import com.plcoding.bookpedia.domain.Book
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedBookViewModel:ViewModel() {

    private val _selectedBook = MutableStateFlow<Book?>(null)
    val selectedBook = _selectedBook.asStateFlow()

    fun setSelectedBook(book: Book?){
        _selectedBook.value = book
    }
}