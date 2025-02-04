package com.plcoding.bookpedia.app

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.plcoding.bookpedia.presentation.SelectedBookViewModel
import com.plcoding.bookpedia.presentation.book_detail.BookDetailAction
import com.plcoding.bookpedia.presentation.book_detail.BookDetailScreenRoot
import com.plcoding.bookpedia.presentation.book_detail.BookDetailViewModel
import com.plcoding.bookpedia.presentation.book_list.BookListScreenRoot
import com.plcoding.bookpedia.presentation.book_list.BookListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.BookGraph
        ) {
            navigation<Route.BookGraph>(
                startDestination = Route.BookList
            ) {
                composable<Route.BookList>(
                    exitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(
                                durationMillis = 750,
                                easing = FastOutSlowInEasing
                            )
                        ) { initialOffset -> initialOffset }
                    },
                    popEnterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(
                                durationMillis = 750,
                                easing = FastOutSlowInEasing
                            )
                        ) { initialOffset -> initialOffset }
                    }
                ) {
                    val viewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel =
                        it.sharedKoinViewModel<SelectedBookViewModel>(navController = navController)

                    LaunchedEffect(Unit) {
                        selectedBookViewModel.setSelectedBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.setSelectedBook(book)
                            navController.navigate(Route.BookDetail(book.id))
                        }
                    )
                }
                composable<Route.BookDetail>(
                    enterTransition = {
                        slideInHorizontally(
                            animationSpec = tween(
                                durationMillis = 750,
                                easing = FastOutSlowInEasing
                            )
                        ) { initialOffSet ->
                            initialOffSet
                        }
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            animationSpec = tween(
                                durationMillis = 750,
                                easing = FastOutSlowInEasing
                            )
                        ) { initialOffSet ->
                            initialOffSet
                        }
                    }
                ) { backStackEntry ->
                    val selectedBookViewModel =
                        backStackEntry.sharedKoinViewModel<SelectedBookViewModel>(navController = navController)
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()
                    val viewModel = koinViewModel<BookDetailViewModel>()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let { selectedBook ->
                            viewModel.onAction(BookDetailAction.OnSelectedBookChanged(selectedBook))
                        }
                    }

                    BookDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClicked = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) { navController.getBackStackEntry(navGraphRoute) }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}