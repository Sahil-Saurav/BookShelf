package com.example.bookshelf.Data.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class BookEntity(
    @PrimaryKey(autoGenerate = false)
    val id : String,
    val title : String,
    val image : String,
    val currentlyReading : Boolean,
    val finishedReading : Boolean
)
