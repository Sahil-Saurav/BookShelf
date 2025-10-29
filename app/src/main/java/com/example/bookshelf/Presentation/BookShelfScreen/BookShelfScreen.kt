package com.example.bookshelf.Presentation.BookShelfScreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookshelf.Common.Filter
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Presentation.BookShelfScreen.Components.AlertBox.AlertBoxDialog
import com.example.bookshelf.Presentation.BookShelfScreen.Components.BookShelfItem.BookShelfItem
import com.example.bookshelf.Presentation.BookShelfScreen.Components.FilterChips.FilterChips
import com.example.bookshelf.Presentation.BookShelfScreen.Components.FilterChips.filterChips
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont

@Composable
fun BookShelfScreen(
    navController: NavController,
    bookShelfViewModel: BookShelfViewModel = hiltViewModel()
){

    val books = bookShelfViewModel.state
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = "BookShelf",
                color = Color.White,
                fontFamily = wdxllubrifont,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
        }
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier =Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            filterChips.onEach { filter ->
                FilterChips(
                    text = filter.name,
                    selected = filter.type == books.value.filter,
                    onClick = {
                        when(filter.type){
                            Filter.ALL -> bookShelfViewModel.getAllBook()
                            Filter.NOT_STARTED -> bookShelfViewModel.getNotStartedBook()
                            Filter.FINISHED -> bookShelfViewModel.getFinishedBook()
                            Filter.CURRENTLY_READING -> bookShelfViewModel.getCurrentlyReadingBook()
                        }
                    }
                )
            }
        }
        var bookToDelete by remember { mutableStateOf<BookEntity>(BookEntity()) }
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ){
            items(books.value.book) { book ->
                BookShelfItem(book,onDeleteClick = {
                    showDialog = true
                    bookToDelete = book
                    Log.i("deleteBook","Click on delete icon")
                }
                )
            }
        }
        if(showDialog){
            AlertBoxDialog(
                onDismiss = {showDialog = false},
                onConfirm = {
                    bookShelfViewModel.deleteBook(bookToDelete)
                    showDialog = false
                }
            )
        }
    }
}