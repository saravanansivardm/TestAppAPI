package com.example.testappapi.network.repository

import com.example.testappapi.network.api.ApiInterface
import com.example.testappapi.network.model.IpResponse
import com.example.testappapi.network.util.Constants
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class IpLookUpRepository @Inject constructor(
    private val apiInterface: ApiInterface,
) {
    suspend fun getIpLookUpResponse(): IpResponse {
        val accessKey = Constants.API_KEY
        return apiInterface.getIpLookUpData(accessKey)
    }
}