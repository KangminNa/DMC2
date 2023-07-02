package com.monthlycoding.dmc2.datas

import java.io.Serializable

data class MenuData(
    val foodId : Int = 1,
    val price : Int = 0,
    val menuName : String = "",
    val menuImgLink : String = ""
): Serializable
