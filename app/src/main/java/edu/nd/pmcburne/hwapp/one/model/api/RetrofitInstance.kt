package edu.nd.pmcburne.hwapp.one.model.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://ncaa-api.henrygd.me/"
    private val json = Json {
        ignoreUnknownKeys = true
    }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            json.asConverterFactory("application/json".toMediaType())
        )
        .build()
    val api: NcaaApiService = retrofit.create(NcaaApiService::class.java)
}