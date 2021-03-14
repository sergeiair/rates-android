package com.sz.ratespal.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sz.ratespal.api.sign.SignApiInteractor
import com.sz.ratespal.api.sign.SignApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://ratespal.me/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideSignService(retrofit: Retrofit): SignApiService = retrofit.create(SignApiService::class.java)

    @Singleton
    @Provides
    fun provideSignApiInteractor(signApiService: SignApiService): SignApiInteractor = SignApiInteractor(signApiService)

}