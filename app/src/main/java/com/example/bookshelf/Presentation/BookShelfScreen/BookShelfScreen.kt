package com.example.bookshelf.Presentation.BookShelfScreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bookshelf.Common.Filter
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Presentation.BookShelfScreen.Components.AlertBox.DeleteAlertBoxDialog
import com.example.bookshelf.Presentation.BookShelfScreen.Components.BookShelfItem.BookShelfItem
import com.example.bookshelf.Presentation.BookShelfScreen.Components.EditBottomSheet.editBottomSheetDropDownItems
import com.example.bookshelf.Presentation.BookShelfScreen.Components.FilterChips.FilterChips
import com.example.bookshelf.Presentation.BookShelfScreen.Components.FilterChips.filterChips
import com.example.bookshelf.Presentation.ui.theme.wdxllubrifont
import com.example.bookshelf.R
import com.example.bookshelf.Utils.CustomAppM3TextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfScreen(
    navController: NavController,
    bookShelfViewModel: BookShelfViewModel = hiltViewModel()
){

    val books = bookShelfViewModel.state
    var showDeleteDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showDropDown by remember { mutableStateOf(false) }
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
        Spacer(modifier = Modifier.height(8.dp))
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
        var bookToBeUpdated by remember { mutableStateOf<BookEntity>(BookEntity()) }
        var bookStatus by remember { mutableStateOf("") }
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ){
            items(books.value.book) { book ->
                BookShelfItem(book,onDeleteClick = {
                    showDeleteDialog = true
                    bookToDelete = book
                    Log.i("deleteBook","Click on delete icon")
                },
                    onUpdateClick = {
                        showBottomSheet = true
                        bookToBeUpdated = book
                        bookStatus = if(book.finishedReading == true) "Finished" else if(book.currentlyReading == true) "Reading" else "Not Started"
                        Log.i("updateBook","Click on update icon")
                    }
                )
            }
        }
        if(showDeleteDialog){
            DeleteAlertBoxDialog(
                onDismiss = {showDeleteDialog = false},
                onConfirm = {
                    bookShelfViewModel.deleteBook(bookToDelete)
                    showDeleteDialog = false
                }
            )
        }
        if(showBottomSheet){
            ModalBottomSheet(
                sheetState = sheetState,
                containerColor = colorResource(R.color.Primary_Font_Green),
                onDismissRequest = {showBottomSheet = false}
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Change the Current Status",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = wdxllubrifont,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    HorizontalDivider(color = Color.White)
                    Spacer(modifier = Modifier.height(16.dp))

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CustomAppM3TextField(
                            value = bookStatus,
                            onValueChange = {bookStatus = it},
                            label = "Status",
                            enabled = false,
                            modifier = Modifier.clickable(
                                onClick = {showDropDown = !showDropDown}
                            )
                        )
                    }
                    AnimatedVisibility(
                        visible = showDropDown,
                        enter = expandVertically(expandFrom = Alignment.Top),
                        exit = shrinkVertically(shrinkTowards = Alignment.Top)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp), // Adjust padding to align with TextField content
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            editBottomSheetDropDownItems.forEach { item ->
                                Text(
                                    text = item.label,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            when(item.type){
                                                Filter.FINISHED -> {
                                                    val update = bookToBeUpdated.copy(finishedReading = true, currentlyReading = false)
                                                    bookShelfViewModel.addBook(update)
                                                    bookStatus = item.label
                                                }
                                                Filter.CURRENTLY_READING -> {
                                                    val update = bookToBeUpdated.copy(currentlyReading = true, finishedReading = false)
                                                    bookShelfViewModel.addBook(update)
                                                    bookStatus = item.label
                                                }
                                                Filter.NOT_STARTED -> {
                                                    val update = bookToBeUpdated.copy(currentlyReading = false, finishedReading = false)
                                                    bookShelfViewModel.addBook(update)
                                                    bookStatus = item.label
                                                }
                                            }
                                            showDropDown = false
                                            showBottomSheet = false
                                        }
                                        .padding(vertical = 12.dp),
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    fontFamily = wdxllubrifont
                                )
                                if (item != editBottomSheetDropDownItems.last()) {
                                    HorizontalDivider(color = Color.White.copy(alpha = 0.2f))
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    /*if(showDropDown) {

                    }*/
                }
            }
        }
    }
}