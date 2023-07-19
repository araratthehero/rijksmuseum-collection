package com.mnatsakanyan.data.di

import com.example.data.BuildConfig
import com.mnatsakanyan.data.network.CollectionService
import com.mnatsakanyan.data.network.interceptor.ApiKeyInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
            .apply {
                if (BuildConfig.DEBUG) {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            }

    @Singleton
    @Provides
    fun providesApiKeyInterceptor() = ApiKeyInterceptor(BuildConfig.API_KEY)

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor,
                            apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient =
            OkHttpClient
                    .Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .addInterceptor(apiKeyInterceptor)
                    .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideCollectionService(retrofit: Retrofit): CollectionService =
            retrofit.create(CollectionService::class.java)
}
