package com.karan.coingecko.demo.common.utils

fun String.addPrefix(prefix: String, ignoreEmpty: Boolean = true): String {
    return if (this.isEmpty() && ignoreEmpty) this
    else if (this.startsWith(prefix)) this
    else "$prefix$this"
}