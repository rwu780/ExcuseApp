package com.rwu780.excuseapp.data.remote

data class ExcuseDto(
    val id: Int,
    val excuse: String,
    val category: String
) {
    companion object{
        val categories = listOf<String>("Random", "Family", "Office", "Children","College", "Party")
    }

}