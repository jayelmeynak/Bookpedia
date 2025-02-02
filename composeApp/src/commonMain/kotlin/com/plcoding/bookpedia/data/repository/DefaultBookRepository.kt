package com.plcoding.bookpedia.data.repository

import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import com.plcoding.bookpedia.data.mappers.toBook
import com.plcoding.bookpedia.data.remote.RemoteBookDataSource
import com.plcoding.bookpedia.domain.Book
import com.plcoding.bookpedia.domain.BookRepository

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.RemoteError> {
        return remoteBookDataSource.searchBooks(query).map { response ->
            response.results.map { dto ->
                dto.toBook()
            }
        }
    }
}