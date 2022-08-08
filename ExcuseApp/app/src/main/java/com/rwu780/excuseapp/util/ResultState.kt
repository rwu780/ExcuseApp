package com.rwu780.excuseapp.util

sealed class ResultState<T> {

    class Loading<T> : ResultState<T>()
    data class Success<T>(val data: T): ResultState<T>()
    class Error<T> (val message: String): ResultState<T>()

}