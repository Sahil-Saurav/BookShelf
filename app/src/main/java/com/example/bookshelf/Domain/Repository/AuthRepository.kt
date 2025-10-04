package com.example.bookshelf.Domain.Repository

import com.example.bookshelf.Common.AuthResult

interface AuthRepository {
    suspend fun signIn(email:String,password:String):AuthResult
    suspend fun signUp(email:String,password:String):AuthResult
    suspend fun signOut()
    fun getCurrentUserId():String?
}