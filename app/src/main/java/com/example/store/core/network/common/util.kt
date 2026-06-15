package com.example.store.core.network.common

import com.example.store.core.network.model.Response
import com.google.gson.Gson
import retrofit2.HttpException

internal fun extractErrorMessage(exception: HttpException): String {
   exception.response()?.errorBody()?.charStream()?.let {
        return Gson().fromJson(it, Response::class.java)?.message ?: "it is null"
   }

    return "null reponse"
}