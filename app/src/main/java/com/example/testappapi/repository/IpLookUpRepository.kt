package com.example.testappapi.repository

import com.example.testappapi.api.ApiInterface
import com.example.testappapi.model.IpResponse
import com.example.testappapi.util.Constants
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