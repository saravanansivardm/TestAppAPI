package com.example.testappapi.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testappapi.network.model.IpResponse
import com.example.testappapi.network.repository.IpLookUpRepository
import com.example.testappapi.network.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IpLookUpViewModel @Inject constructor(
    private val ipLookUpRepository: IpLookUpRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)

    private val _getIpData = MutableStateFlow<Resource<IpResponse>>(Resource.Loading)
    val getIpData: StateFlow<Resource<IpResponse>> get() = _getIpData

    init {
        fetchDataFromApi()
    }

    fun fetchDataFromApi() {
        viewModelScope.launch {
            getIpLookUpData()
        }
    }

    private fun getIpLookUpData() {
        viewModelScope.launch {
            _getIpData.value = Resource.Loading
            try {
                val response = ipLookUpRepository.getIpLookUpResponse()
                _getIpData.value = Resource.Success(response)
            } catch (e: Exception) {
                _getIpData.value = Resource.Error(e.message ?: "Unknown error")
            }
        }
    }
}