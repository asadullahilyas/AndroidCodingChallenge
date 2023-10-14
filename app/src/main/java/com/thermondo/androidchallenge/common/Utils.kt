package com.thermondo.androidchallenge.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> fromJson(jsonString: String): T? {
    return Moshi.Builder().build().adapter<T>().fromJson(jsonString)
}

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T> toJson(t: T): String {
    return Moshi.Builder().build().adapter<T>().toJson(t)
}