package com.example.bookshelf.Data.Repository

import android.util.Log
import com.example.bookshelf.Common.AuthResult
import com.example.bookshelf.Domain.Repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override suspend fun signIn(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            val userId = result.user?.uid
            if (userId != null){
                AuthResult.Success(userId)
            }else{
                AuthResult.Error("Signed In but cannot found user id")
            }
        } catch (e : Exception){
            AuthResult.Error(e.message ?: "An Error Occurred")
        }
    }

    override suspend fun signUp(email: String, password: String): AuthResult {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email,password).await()
            val userId = result.user?.uid
            if(userId != null){
                AuthResult.Success(userId)
            }else{
                AuthResult.Error("Signed Up but cannot found user id")
            }
        } catch (e:Exception){
            AuthResult.Error(e.message ?: "An unknown error occurred")
        }
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
        Log.i("signout","repository signout")
    }

    override fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

}