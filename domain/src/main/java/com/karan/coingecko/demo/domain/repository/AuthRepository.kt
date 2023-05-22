package com.karan.coingecko.demo.domain.repository

interface AuthRepository {
    suspend fun login()
}