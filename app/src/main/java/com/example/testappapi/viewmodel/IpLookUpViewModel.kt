package com.example.testappapi.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappapi.model.IpResponse
import com.example.testappapi.repository.IpLookUpRepository
import com.example.testappapi.util.Constants
import com.example.testappapi.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class IpLookUpViewModel @Inject constructor(
    private val ipLookUpRepository: IpLookUpRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)

    private val _getIpData: MutableLiveData<IpResponse> =
        MutableLiveData<IpResponse>()
    val getIpData: LiveData<IpResponse>
        get() = _getIpData

    init {
        fetchDataFromApi()
    }

    fun fetchDataFromApi() {
        Dispatchers.IO
        viewModelScope.launch(Dispatchers.IO) {
            getIpLookUpData()
        }
    }

    private suspend fun getIpLookUpData(): Resource<IpResponse> {
        val accessKey = Constants.API_KEY
        val result = ipLookUpRepository.getIpLookUpResponse(accessKey)
        when (result) {
            is Resource.Loading -> {
                isLoading.value = false
            }

            is Resource.Success -> {
                isLoading.value = true
                withContext(Dispatchers.Main) {
                    _getIpData.value = result.data
                }
            }

            is Resource.Error -> {
                isLoading.value = true
            }
        }
        return result
    }
}