package com.example.bookshelf.Data.Repository

import android.util.Log
import com.example.bookshelf.Common.Constants
import com.example.bookshelf.Data.Local.BookDao
import com.example.bookshelf.Data.Local.BookEntity
import com.example.bookshelf.Domain.Repository.FireStoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireStoreRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore,
    private val bookDao : BookDao
) : FireStoreRepository  {
    override suspend fun addUser(uid: String) {
        val emptyData = emptyMap<String,Any>()
        try {
            db.collection(Constants.COLLECTION_NAME)
                .document(uid)
                .set(emptyData,SetOptions.merge())
            Log.i("userCreate","User created $uid")
        }catch (e: Exception){
            Log.i("userCreate",e.localizedMessage?.toString()?:"User not created")
        }
    }

    override suspend fun addBook(
        uid: String,
        bookEntity: BookEntity
    ) {
        try {
            db.collection(Constants.COLLECTION_NAME)
                .document(uid)
                .collection(Constants.SUB_COLLECTION_NAME)
                .document(bookEntity.id)
                .set(bookEntity)
                .await()
            bookDao.upsertBook(listOf(bookEntity))
            Log.i("addBook","Book added Successfully")
        }catch (e: Exception){
            Log.i("addBook",e.message.toString())
        }
    }

    override suspend fun deleteBook(
        uid: String,
        bookEntity: BookEntity
    ) {
        try {
            db.collection(Constants.COLLECTION_NAME)
                .document(uid)
                .collection(Constants.SUB_COLLECTION_NAME)
                .document(bookEntity.id)
                .delete()
                .await()
            bookDao.deleteBook(bookEntity)
            Log.i("deleteBook","Book Deleted Successfully")
        }catch (e: Exception){
            Log.i("deleteBook",e.message.toString())
        }
    }

    override suspend fun getBook(uid: String): Flow<List<BookEntity>> {
        try {
            val querySnapshot = db.collection(Constants.COLLECTION_NAME)
                .document(uid)
                .collection(Constants.SUB_COLLECTION_NAME)
                .get()
                .await()
            val books = querySnapshot.toObjects(BookEntity::class.java)

            if (books.isNotEmpty()) {
                bookDao.upsertBook(books)
                Log.i("getBook", "Fetched and upserted ${books.size} books")
            } else {
                Log.i("getBook", "No books found in Firestore for user $uid")
            }

        } catch (e: Exception) {
            Log.i("getBook", "Failed to fetch from Firestore: ${e.message}")
        }

        return bookDao.getAllBooks()
    }
}