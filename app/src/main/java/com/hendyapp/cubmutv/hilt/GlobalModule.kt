package com.hendyapp.cubmutv.hilt

import android.content.Context
import com.google.gson.Gson
import com.hendyapp.cubmutv.repo.GlobalRepository
import com.hendyapp.cubmutv.retrofit.ApiClients
import com.hendyapp.cubmutv.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GlobalModule {
    @Singleton
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(30, TimeUnit.SECONDS)
        builder.connectTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        return builder.addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_API_URL)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClients(retrofit: Retrofit): ApiClients {
        return retrofit.create(ApiClients::class.java)
    }

    @Singleton
    @Provides
    fun provideGlobalRepository(clients: ApiClients): GlobalRepository {
        return GlobalRepository(clients)
    }
}