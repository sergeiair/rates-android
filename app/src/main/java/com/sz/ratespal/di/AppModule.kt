package com.sz.ratespal.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sz.ratespal.api.authorized.AuthorizedApiInteractor
import com.sz.ratespal.api.authorized.AuthorizedApiService
import com.sz.ratespal.api.interceptors.AddHeaderInterceptor
import com.sz.ratespal.api.interceptors.ErrorsHandlingInterceptor
import com.sz.ratespal.api.sign.SignApiInteractor
import com.sz.ratespal.api.sign.SignApiService
import com.sz.ratespal.storage.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
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
        .client(
            OkHttpClient.Builder()
                .addInterceptor(ErrorsHandlingInterceptor())
                .addInterceptor(AddHeaderInterceptor())
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideSignApiService(retrofit: Retrofit): SignApiService =
        retrofit.create(SignApiService::class.java)

    @Singleton
    @Provides
    fun provideSignApiInteractor(signApiService: SignApiService): SignApiInteractor =
        SignApiInteractor(signApiService)

    @Singleton
    @Provides
    fun provideAuthorizedApiService(retrofit: Retrofit): AuthorizedApiService =
        retrofit.create(AuthorizedApiService::class.java)

    @Singleton
    @Provides
    fun provideAuthorizedApiInteractor(signApiService: AuthorizedApiService): AuthorizedApiInteractor =
        AuthorizedApiInteractor(signApiService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

}