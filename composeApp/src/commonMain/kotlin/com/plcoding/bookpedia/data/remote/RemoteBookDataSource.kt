package com.plcoding.bookpedia.data.remote

import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.data.dto.BookWorkDto
import com.plcoding.bookpedia.data.dto.SearchResponseDto

interface RemoteBookDataSource {

    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.RemoteError>

    suspend fun getBookDetails(
        bookId: String
    ): Result<BookWorkDto, DataError.RemoteError>
}