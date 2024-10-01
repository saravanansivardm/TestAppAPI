package com.example.testappapi.util

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val data: T) : Resource<T>()
    class Error<T>(message: String, data: T? = null): Resource<T>()

}

