package com.rwu780.excuseapp.domain.repository

import com.rwu780.excuseapp.data.remote.ExcuseDto

interface ExcuseRepository {

    suspend fun getRandomExcuse(num: String) : List<ExcuseDto>

    suspend fun getExcuseWithCategory(category: String, num: String) : List<ExcuseDto>

}