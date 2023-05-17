package com.karan.coingecko.demo.data.network

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.newBuilder().addHeader("X-CoinAPI-Key", Constants.API_KEY).build()
        return chain.proceed(request)
    }
}