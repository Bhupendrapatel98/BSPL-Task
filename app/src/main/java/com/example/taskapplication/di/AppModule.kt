package com.example.taskapplication.di

import com.example.taskapplication.data.api.PostApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by bhupendrapatel on 31/05/22.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl(): String = "https://tennistest.azurewebsites.net/interview/"

    @Provides
    @Singleton
    fun provideRetrofitBuilder(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideApiPostService(retrofit: Retrofit): PostApiService =
        retrofit.create(PostApiService::class.java)
}