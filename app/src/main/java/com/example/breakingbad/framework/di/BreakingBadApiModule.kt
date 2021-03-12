package com.example.breakingbad.framework.di

import com.example.breakingbad.framework.datasource.network.api.BreakingBadApi
import com.example.breakingbad.framework.datasource.network.interceptor.AuthHeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class BreakingBadApiModule {

    @Provides
    fun provideBreakingBadApi(retrofit: Retrofit): BreakingBadApi {
        return retrofit.create(BreakingBadApi::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(com.example.breakingbad.BuildConfig.BREAKING_BAD_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthHeaderInterceptor(com.example.breakingbad.BuildConfig.BREAKING_BAD_API_KEY))
            .build()
    }

    @Provides
    fun provideGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

}