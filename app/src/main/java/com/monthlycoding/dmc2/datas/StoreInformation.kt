package com.monthlycoding.dmc2.datas

import java.io.Serializable

data class StoreInformation(
    val title: String,
    val address: String,
    val openTime: String,
    val location: String,
    val vacation: String,
    val telephone: String,
): Serializable
