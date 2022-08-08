package com.rwu780.excuseapp.domain.usecases

import com.rwu780.excuseapp.domain.repository.ExcuseRepository
import com.rwu780.excuseapp.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetExcuse @Inject constructor(
    private val repository: ExcuseRepository
) {
    operator fun invoke(category: String, num: String) = flow {

        emit(ResultState.Loading())
        try{
            if (category.isBlank() || category == "Random"){
                val excuseList = repository.getRandomExcuse(num)
                if (excuseList.isEmpty()){
                    emit(ResultState.Error("No data available"))
                    return@flow
                }
                emit(ResultState.Success(excuseList))
                return@flow
            }

            val excuseList = repository.getExcuseWithCategory(category, num)
            if (excuseList.isEmpty()){
                emit(ResultState.Error("No data available"))
                return@flow
            }
            emit(ResultState.Success(excuseList))
            return@flow

        } catch (e: Exception) {
            emit(ResultState.Error("Unable to fetch data"))
        }
    }.flowOn(Dispatchers.IO)
}