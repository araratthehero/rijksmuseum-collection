package com.mnatsakanyan.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request
                .url
                .newBuilder()
                .addQueryParameter(QUERY_PARAM_API_KEY, apiKey)
                .build()

        request = request.newBuilder().url(url).build()

        return chain.proceed(request)
    }

    companion object {
        private const val QUERY_PARAM_API_KEY = "key"
    }
}
