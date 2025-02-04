package com.plcoding.bookpedia.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BookDbo::class], version = 1)
@TypeConverters(StringListTypeConverter::class)
@ConstructedBy(BookDatabaseConstructor::class)
abstract class FavoriteBookDatabase() : RoomDatabase() {

    abstract val favoriteBookDao: FavoriteBookDao

    companion object{
        const val DB_NAME = "book_db"
    }
}