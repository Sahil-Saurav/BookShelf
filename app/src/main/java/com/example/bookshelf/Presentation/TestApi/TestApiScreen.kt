package com.example.bookshelf.Presentation.TestUI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bookshelf.Presentation.TestApi.TestApiViewModel

@Composable
fun TestApiScreen(
    viewModel: TestApiViewModel = hiltViewModel(),
) {
    var bookName by remember { mutableStateOf("") }
    var authorName by remember { mutableStateOf("") }
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = bookName,
            onValueChange = { bookName = it },
            label = { Text("Book Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = authorName,
            onValueChange = { authorName = it },
            label = { Text("Author Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.searchBookByNameAndAuthor(bookName, authorName)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }

            state.books?.items.isNullOrEmpty() -> {
                Text(
                    text = "Not Found!!",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.books?.items ?: emptyList()) { book ->
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = book.volumeInfo.title, style = MaterialTheme.typography.titleMedium)
                            Text(text = "Authors:")
                            book.volumeInfo.authors.forEach {
                                Text(text = it)
                            }
                            Divider(modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }
                }
            }
        }
    }
}

