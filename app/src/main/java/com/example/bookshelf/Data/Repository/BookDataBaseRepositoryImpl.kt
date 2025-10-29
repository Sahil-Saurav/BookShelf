package com.example.bookshelf.Data.Repository

import com.example.bookshelf.Data.Local.BookDao
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.BookDataBaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookDataBaseRepositoryImpl @Inject constructor(
    private val bookDao: BookDao
) : BookDataBaseRepository {
    override suspend fun addBook(bookEntity: List<BookEntity>) {
        bookDao.upsertBook(bookEntity)
    }

    override fun getAllBooks(): Flow<List<BookEntity>> {
        return bookDao.getAllBooks()
    }

    override suspend fun deleteBook(bookEntity: BookEntity) {
        bookDao.deleteBook(bookEntity)
    }

    override fun getCountOfFinishedBook(): Flow<Int> {
        return bookDao.getCountOfFinishedBookCount()
    }

    override fun getCountOfCurrentlyReadingBook(): Flow<Int> {
        return bookDao.getCountOfReadingBookCount()
    }

    override fun getCountOfNotReadingBook(): Flow<Int> {
        return bookDao.getCountOfNotStartedBook()
    }

    override fun getFinishedBook(): Flow<List<BookEntity>> {
        return bookDao.getFinishedBook()
    }

    override fun getCurrentlyReadingBook(): Flow<List<BookEntity>> {
        return bookDao.getCurrentlyReadingBook()
    }

    override fun getNotReadingBook(): Flow<List<BookEntity>> {
        return bookDao.getNotStartedBook()
    }

    override fun doesBookExist(bookID: String): Flow<Boolean> {
        return bookDao.doesBookExist(bookID)
    }

}