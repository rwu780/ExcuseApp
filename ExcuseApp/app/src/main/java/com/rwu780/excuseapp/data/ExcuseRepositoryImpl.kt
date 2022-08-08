package com.rwu780.excuseapp.data

import com.rwu780.excuseapp.data.remote.ExcuseApi
import com.rwu780.excuseapp.data.remote.ExcuseDto
import com.rwu780.excuseapp.domain.repository.ExcuseRepository
import javax.inject.Inject

class ExcuseRepositoryImpl @Inject constructor(
    private val api: ExcuseApi,
) : ExcuseRepository {

    @Throws
    override suspend fun getRandomExcuse(num: String): List<ExcuseDto> {

        return api.getRandomExcuse(num)

    }

    @Throws
    override suspend fun getExcuseWithCategory(category: String, num: String): List<ExcuseDto> {

        return api.getExcuseWithCategory(
            category.lowercase(),
            num
        )
    }
}