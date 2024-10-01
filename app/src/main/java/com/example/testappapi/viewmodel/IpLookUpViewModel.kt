package com.example.testappapi.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappapi.R
import com.example.testappapi.model.IpResponse
import com.example.testappapi.repository.IpLookUpRepository
import com.example.testappapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IpLookUpViewModel @Inject constructor(
    private val application: Application,
    private val ipLookUpRepository: IpLookUpRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)

    private var _getIpData: MutableLiveData<IpResponse> =
        MutableLiveData<IpResponse>()
    var getIpData: LiveData<IpResponse> = _getIpData

    init {
        fetchDataFromApi()
    }

    fun fetchDataFromApi() {
        viewModelScope.launch {
            getIpLookUpData()
        }
    }

    private suspend fun getIpLookUpData(): Resource<IpResponse> {
        val accessKey = application.getString(R.string.api_key_txt)
        val result = ipLookUpRepository.getIpLookUpResponse(accessKey)
        when (result) {
            is Resource.Loading -> {
                isLoading.value = false
            }

            is Resource.Success -> {
                isLoading.value = true
                _getIpData.value = result.data
            }

            is Resource.Error -> {
                isLoading.value = true
            }
        }
        return result
    }
}