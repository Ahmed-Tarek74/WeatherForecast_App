package com.get_ready.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl = originalRequest.url()

        // Modify the URL to add the 'key' (API key)
        val newHttpUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("key", apiToken)
            .build()

        // Build a new request with the modified URL
        val requestWithToken = originalRequest.newBuilder()
            .url(newHttpUrl)
            .build()

        return chain.proceed(requestWithToken)
    }
}