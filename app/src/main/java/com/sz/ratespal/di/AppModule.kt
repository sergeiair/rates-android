package com.sz.ratespal.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sz.ratespal.api.sign.SignApiInteractor
import com.sz.ratespal.api.sign.SignApiService
import com.sz.ratespal.storage.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

}