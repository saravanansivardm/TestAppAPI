package com.example.testappapi.api

import com.example.testappapi.model.IpResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("/134.201.250.155")
    suspend fun getIpLookUpData(@Query("access_key") accessKey: String): IpResponse
}