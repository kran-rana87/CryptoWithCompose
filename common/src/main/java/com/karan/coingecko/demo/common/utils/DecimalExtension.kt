package com.karan.coingecko.demo.common.utils

import java.text.DecimalFormat
import kotlin.math.round

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}


fun Double.addComma(): String {
    val dform = DecimalFormat("#,###.####")
    return dform.format(this)

}