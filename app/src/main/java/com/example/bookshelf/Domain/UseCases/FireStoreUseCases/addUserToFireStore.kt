package com.example.bookshelf.Domain.UseCases.FireStoreUseCases

import com.example.bookshelf.Domain.Repository.FireStoreRepository
import javax.inject.Inject

class addUserToFireStore @Inject constructor(
    private val fireStoreRepository: FireStoreRepository
) {
    suspend operator fun invoke(uid: String){
        fireStoreRepository.addUser(uid)
    }
}