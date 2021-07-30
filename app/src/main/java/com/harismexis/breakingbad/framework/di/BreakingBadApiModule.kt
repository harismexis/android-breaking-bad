package com.harismexis.breakingbad.framework.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.harismexis.breakingbad.BuildConfig
import com.harismexis.breakingbad.framework.datasource.network.api.BreakingBadApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class BreakingBadApiModule {

    @Provides
    @Singleton
    fun provideBreakingBadApi(retrofit: Retrofit): BreakingBadApi {
        return retrofit.create(BreakingBadApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BREAKING_BAD_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideGSON(): Gson {
        return GsonBuilder().setLenient().create()
    }

}