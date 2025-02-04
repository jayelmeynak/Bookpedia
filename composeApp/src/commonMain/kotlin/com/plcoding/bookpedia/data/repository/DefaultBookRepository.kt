package com.plcoding.bookpedia.data.repository

import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import com.plcoding.bookpedia.data.database.FavoriteBookDao
import com.plcoding.bookpedia.data.mappers.toBook
import com.plcoding.bookpedia.data.mappers.toBookDbo
import com.plcoding.bookpedia.data.remote.RemoteBookDataSource
import com.plcoding.bookpedia.domain.Book
import com.plcoding.bookpedia.domain.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.sqlite.SQLiteException

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favoriteBookDao: FavoriteBookDao
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.RemoteError> {
        return remoteBookDataSource.searchBooks(query).map { response ->
            response.results.map { dto ->
                dto.toBook()
            }
        }
    }

    override suspend fun getBookDetails(bookId: String): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBook(bookId)

        return if(localResult == null) {
            remoteBookDataSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookDbo ->
                bookDbo.map { it.toBook() }
            }
    }

    override fun isBookFavorite(id: String): Flow<Boolean> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == id }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.LocalError> {
        return try {
            favoriteBookDao.upsertBook(book.toBookDbo())
            Result.Success(Unit)
        } catch(e: SQLiteException) {
            Result.Error(DataError.LocalError.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(id: String) {
        favoriteBookDao.deleteFavoriteBook(id)
    }
}