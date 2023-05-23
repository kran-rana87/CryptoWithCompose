package com.karan.coingecko.demo.common.utils

fun String.addPrefix(prefix: String, ignoreEmpty: Boolean = true): String {
    if (this.isEmpty() && ignoreEmpty) return this
    else if (this.startsWith(prefix)) return this
    return "$prefix$this"
}