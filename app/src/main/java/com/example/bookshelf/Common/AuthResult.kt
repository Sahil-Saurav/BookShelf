package com.example.bookshelf.Common

sealed class AuthResult {
    data class Success(val userId:String) : AuthResult()
    data class Error(val message: String) : AuthResult()
    object Loading : AuthResult()
    object Idle : AuthResult()
}