package com.rwu780.excuseapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface ExcuseApi {

    @GET("excuse/{category}/{num}")
    suspend fun getExcuseWithCategory(
        @Path("category") category: String,
        @Path("num") number: String
    ) : List<ExcuseDto>

    @GET("excuse/{num}")
    suspend fun getRandomExcuse(
        @Path("num") number: String
    ) : List<ExcuseDto>

    companion object {
        const val BASE_URL = "https://excuser.herokuapp.com/v1/"
    }

}