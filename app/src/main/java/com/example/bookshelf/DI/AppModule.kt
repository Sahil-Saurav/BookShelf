package com.example.bookshelf.DI

import android.content.Context
import androidx.room.Room
import com.example.bookshelf.Application
import com.example.bookshelf.Common.Httpdetails
import com.example.bookshelf.Data.GoogleBooksApi
import com.example.bookshelf.Data.Local.BookDao
import com.example.bookshelf.Data.Local.BookDataBase
import com.example.bookshelf.Data.Repository.AuthRepositoryImpl
import com.example.bookshelf.Data.Repository.BookDataBaseRepositoryImpl
import com.example.bookshelf.Data.Repository.BookShelfRepositoryImpl
import com.example.bookshelf.Data.Repository.FireStoreRepositoryImpl
import com.example.bookshelf.Data.Repository.SettingsRepositoryImpl
import com.example.bookshelf.Domain.Repository.AuthRepository
import com.example.bookshelf.Domain.Repository.BookDataBaseRepository
import com.example.bookshelf.Domain.Repository.BookShelfRepository
import com.example.bookshelf.Domain.Repository.FireStoreRepository
import com.example.bookshelf.Domain.Repository.SettingsRepository
import com.example.bookshelf.Domain.UseCases.AuthUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCountUseCase.GetCountCurrentlyReadingBookUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCountUseCase.GetCountFinishedBookUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCountUseCase.GetCountNotStartedBookUseCase
import com.example.bookshelf.Domain.UseCases.BookDatabaseUseCase.GetCountUseCase.GetCountUseCase
import com.example.bookshelf.Domain.UseCases.GetCurrentUserIdUseCase.GetCurrentUserIdUseCase
import com.example.bookshelf.Domain.UseCases.SignInUseCase.SignInUseCase
import com.example.bookshelf.Domain.UseCases.SignOutUseCase.SignOutUseCase
import com.example.bookshelf.Domain.UseCases.SignUpUseCase.SignUpUseCase
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesGoogleBooksApi(): GoogleBooksApi{
        return Retrofit.Builder()
            .baseUrl(Httpdetails.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleBooksApi::class.java)
    }

    @Provides
    @Singleton
    fun providesBookShelfRepository(api : GoogleBooksApi): BookShelfRepository{
        return BookShelfRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth():FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun providesAuthRepository(firebaseAuth: FirebaseAuth):AuthRepository{
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesOnBoardingRepository(@ApplicationContext context: Context): SettingsRepository{
        return SettingsRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun providesAuthUseCase(repository: AuthRepository): AuthUseCase{
        return AuthUseCase(
            SignInUseCase = SignInUseCase(repository),
            SignUpUseCase = SignUpUseCase(repository),
            SignOutUseCase = SignOutUseCase(repository),
            GetCurrentUserIdUseCase = GetCurrentUserIdUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun providesBookDataBase(@ApplicationContext context: Context): BookDataBase{
        return Room.databaseBuilder(
            context,
            BookDataBase::class.java,
            "book_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesBookDao(db: BookDataBase) : BookDao{
        return db.bookDao()
    }

    @Provides
    @Singleton
    fun providesBookDataBaseRepository(bookDao: BookDao) : BookDataBaseRepository{
        return BookDataBaseRepositoryImpl(bookDao)
    }

    @Provides
    @Singleton
    fun providesFirebaseFireStore(): FirebaseFirestore {
        return Firebase.firestore
    }

    @Provides
    @Singleton
    fun providesFireBaseFireStoreRepository(db: FirebaseFirestore,bookDao: BookDao): FireStoreRepository{
        return FireStoreRepositoryImpl(db,bookDao)
    }

    @Provides
    @Singleton
    fun providesGetCountUseCase(bookDataBaseRepository: BookDataBaseRepository) : GetCountUseCase {
        return GetCountUseCase(
            getCountFinishedBookUseCase = GetCountFinishedBookUseCase(bookDataBaseRepository),
            getCountCurrentlyReadingBookUseCase = GetCountCurrentlyReadingBookUseCase(bookDataBaseRepository),
            getCountNotStartedBookUseCase = GetCountNotStartedBookUseCase(bookDataBaseRepository)
        )
    }
}