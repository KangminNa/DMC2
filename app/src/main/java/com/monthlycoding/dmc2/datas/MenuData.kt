package com.monthlycoding.dmc2.datas

import java.io.Serializable

data class MenuData(
    val id : Int = 1,
    val price : Int = 0,
    val menu : String = "",
    val menuImgLink : String = ""
): Serializable
