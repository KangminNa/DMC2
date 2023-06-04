package com.monthlycoding.dmc2.datas

import java.io.Serializable

data class CategoryData(
    val id: Int = 0,
    val name: String = "",
    val phone: String = "",
    val location: String = "",
    val foodGroup: String = "",
    val operatingTime: String = "",
    val closed: String = "",
    val imgLink: String = "",
): Serializable