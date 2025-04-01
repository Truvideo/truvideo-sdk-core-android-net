package com.truvideo.core

interface CoreCallback {
    fun onSuccess(result: String?)
    fun onFailure(error: String?)
}