package com.example.bookshelf.DI

import com.example.bookshelf.Common.Httpdetails
import com.example.bookshelf.Data.GoogleBooksApi
import com.example.bookshelf.Data.Repository.BookShelfRepositoryImpl
import com.example.bookshelf.Domain.Repository.BookShelfRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
}