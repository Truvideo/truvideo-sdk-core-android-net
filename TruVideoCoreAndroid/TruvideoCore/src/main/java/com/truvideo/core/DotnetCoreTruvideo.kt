package com.truvideo.core

import android.content.Context
import androidx.startup.AppInitializer
import com.truvideo.sdk.core.TruvideoSdk
import com.truvideo.sdk.core.TruvideoSdkInitializer
import com.truvideo.sdk.core.interfaces.TruvideoSdkCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import truvideo.sdk.common.exceptions.TruvideoSdkException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class DotnetCoreTruvideo {
    companion object {

        @JvmStatic
        fun environment(callback: CoreCallback) {
            val environment = TruvideoSdk.environment
            callback.onSuccess("" + environment)
        }

        @JvmStatic
        fun version(callback: CoreCallback) {
            val version = TruvideoSdk.version
            callback.onSuccess("" + version)
        }

        @JvmStatic
        fun getApiKey(callback: CoreCallback) {
            val apiKey = TruvideoSdk.getApiKey()
            callback.onSuccess("" + apiKey)
        }

        @JvmStatic
        fun initAppInitializer(context: Context, callback: CoreCallback) {
            GlobalScope.launch(Dispatchers.Main) {
                AppInitializer.getInstance(context.applicationContext)
                    .initializeComponent(TruvideoSdkInitializer::class.java)
                callback.onSuccess("Init success")
            }
        }

        @JvmStatic
        fun isAuthenticated(callback: CoreCallback) {
            val isAuthenticated = TruvideoSdk.isAuthenticated()
            callback.onSuccess("" + isAuthenticated)
        }

        @JvmStatic
        fun isAuthenticationExpired(callback: CoreCallback) {
            val isAuthenticationExpired = TruvideoSdk.isAuthenticationExpired()
            callback.onSuccess("" + isAuthenticationExpired)
        }

        @JvmStatic
        fun generatePayload(callback: CoreCallback) {
            val generatePayload = TruvideoSdk.generatePayload()
            callback.onSuccess(generatePayload)
        }

        @JvmStatic
        fun authenticate(
            apiKey: String,
            payload: String,
            signature: String,
            externalId: String,
            callback: CoreCallback
        ) {
            // Replace with your actual condition logic
            TruvideoSdk.authenticate(
                apiKey = apiKey,
                payload = payload,
                signature = signature,
                externalId = externalId,
                callback = object : TruvideoSdkCallback<Unit> {
                    override fun onComplete(result: Unit) {
                        callback.onSuccess("Authentication Success")
                    }

                    override fun onError(exception: TruvideoSdkException) {
                        callback.onFailure(exception.toString())
                    }
                }
            )
        }

        @JvmStatic
        fun initAuthentication(callback: CoreCallback) {
            TruvideoSdk.initAuthentication(callback = object : TruvideoSdkCallback<Unit> {
                override fun onComplete(result: Unit) {
                    callback.onSuccess("Init Authentication Success")
                }

                override fun onError(exception: TruvideoSdkException) {
                    callback.onFailure(exception.toString())
                }
            })
        }


        @JvmStatic
        fun clearAuthentication(callback: CoreCallback) {
            val clearAuthentication = TruvideoSdk.clearAuthentication()
            callback.onSuccess("Authentication Clear")
        }
    }

}