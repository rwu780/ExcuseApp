package com.rwu780.excuseapp.data.remote

import com.squareup.moshi.Json

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