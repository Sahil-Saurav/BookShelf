package com.example.bookshelf.Data.Local

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDataBase : RoomDatabase() {
    abstract fun bookDao() : BookDao
}