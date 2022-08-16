package com.rwu780.excuseapp.data.remote

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ExcuseDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "excuse")
    val excuse: String,
    @Json(name = "category")
    val category: String
) {
    companion object{
        val categories = listOf<String>("Random", "Family", "Office", "Children","College", "Party")
    }

}