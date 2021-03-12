package com.example.breakingbad.framework.datasource.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthHeaderInterceptor(
    private val apiKey: String
) : Interceptor {

    companion object {
        const val AUTH_HEADER = "x-api-key"
    }

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.run {
            proceed(
                request()
                    .newBuilder()
                    .addHeader(AUTH_HEADER, apiKey)
                    .build()
            )
        }
}