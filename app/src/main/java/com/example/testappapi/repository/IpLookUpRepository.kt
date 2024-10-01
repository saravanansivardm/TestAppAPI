package com.example.testappapi.repository

import com.example.testappapi.api.ApiInterface
import com.example.testappapi.model.IpResponse
import com.example.testappapi.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class IpLookUpRepository @Inject constructor(
    private val apiInterface: ApiInterface,
) {
    suspend fun getIpLookUpResponse(accessKey: String): Resource<IpResponse> {
        val response = try {
            apiInterface.getIpLookUpData(accessKey)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occured: ${e.localizedMessage}")
        }
        return Resource.Success(response)
    }

}