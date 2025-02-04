package com.plcoding.bookpedia.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteBookDao {

    @Upsert
    suspend fun upsertBook(book: BookDbo)

    @Query("SELECT * FROM BookDbo")
    fun getFavoriteBooks(): Flow<List<BookDbo>>

    @Query("SELECT * FROM BookDbo WHERE id = :bookId")
    suspend fun getFavoriteBook(bookId: String): BookDbo?

    @Query("DELETE FROM BookDbo WHERE id = :bookId")
    suspend fun deleteFavoriteBook(bookId: String)
}