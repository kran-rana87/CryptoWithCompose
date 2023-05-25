package com.karan.coingecko.demo.network.retrofit

import com.karan.coingecko.demo.common.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader("X-CoinAPI-Key", Constants.API_KEY).build()
        return chain.proceed(request)
    }
}