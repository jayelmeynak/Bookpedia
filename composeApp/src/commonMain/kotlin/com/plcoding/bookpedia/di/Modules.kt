package com.plcoding.bookpedia.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.plcoding.bookpedia.core.data.HttpClientFactory
import com.plcoding.bookpedia.data.database.DatabaseFactory
import com.plcoding.bookpedia.data.database.FavoriteBookDatabase
import com.plcoding.bookpedia.data.remote.KtorRemoteBookDataSource
import com.plcoding.bookpedia.data.remote.RemoteBookDataSource
import com.plcoding.bookpedia.data.repository.DefaultBookRepository
import com.plcoding.bookpedia.domain.BookRepository
import com.plcoding.bookpedia.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.presentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.presentation.book_list.BookListViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single {
        HttpClientFactory.create(get())
    }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single {
        get<FavoriteBookDatabase>().favoriteBookDao
    }

    viewModelOf(::BookListViewModel)
    viewModelOf(::SelectedBookViewModel)
    viewModelOf(::BookDetailViewModel)

}