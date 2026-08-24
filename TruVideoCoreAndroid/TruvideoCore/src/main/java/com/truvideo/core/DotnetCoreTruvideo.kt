package com.truvideo.core

import android.content.Context
import androidx.startup.AppInitializer
import com.truvideo.sdk.core.TruvideoSdk
import com.truvideo.sdk.core.initializer.TruvideoSdkInitializer
import com.truvideo.sdk.core.interfaces.TruvideoSdkCallback
import com.truvideo.sdk.model.exceptions.TruvideoSdkException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


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

        /*@JvmStatic
        fun getApiKey(callback: CoreCallback) {
            val apiKey = TruvideoSdk.getApiKey()
            callback.onSuccess("" + apiKey)
        }*/

        @JvmStatic
        fun initAppInitializer(context: Context, callback: CoreCallback) {
            GlobalScope.launch(Dispatchers.IO) {
                AppInitializer.getInstance(context.applicationContext)
                    .initializeComponent(TruvideoSdkInitializer::class.java)
                callback.onSuccess("Init success")
            }
        }


        @JvmStatic
        fun isAuthenticated(callback: CoreCallback) {
            try {
                val isAuthenticated = TruvideoSdk.isAuthenticated
                callback.onSuccess("" + isAuthenticated)
            } catch (e: Exception) {
                callback.onFailure(e.message ?: "Unknown error")
            }
        }

        @JvmStatic
        fun isAuthenticationExpired(callback: CoreCallback) {
            //val isAuthenticationExpired = TruvideoSdk.isAuthenticationExpired()
            val isAuthenticationExpired = false;
            callback.onSuccess("" + isAuthenticationExpired)
        }


        @JvmStatic
        fun generatePayload(callback: CoreCallback) {
            try {
                val payload = TruvideoSdk.generatePayload()
                callback.onSuccess(payload)
            } catch (e: Exception) {
                callback.onFailure(e.message ?: "Unknown error")
            }
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



        /*@JvmStatic
        fun initAuthentication(callback: CoreCallback) {
            TruvideoSdk.initAuthentication(callback = object : TruvideoSdkCallback<Unit> {
                override fun onComplete(result: Unit) {
                    callback.onSuccess("Init Authentication Success")
                }

                override fun onError(exception: TruvideoSdkException) {
                    callback.onFailure(exception.toString())
                }
            })
        }*/


        @JvmStatic
        fun clearAuthentication(callback: CoreCallback) {
            try {
                TruvideoSdk.clearAuthentication(
                    callback = object : TruvideoSdkCallback<Unit> {

                        override fun onComplete(result: Unit) {
                            callback.onSuccess("Authentication Cleared")
                        }

                        override fun onError(
                            exception: TruvideoSdkException
                        ) {
                            callback.onFailure(
                                exception.message ?: exception.toString()
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                callback.onFailure(e.message ?: "Unknown error")
            }
        }

        /*@JvmStatic
        fun restoreAuthentication(callback: CoreCallback) {
            val restoreAuthentication = TruvideoSdk.restoreAuthentication()
            callback.onSuccess("restoreAuthentication")
        }*/


        @JvmStatic
        fun authenticateOtp(
            otp: String,
            callback: CoreCallback
        ) {
            try {
                TruvideoSdk.authenticate(
                    otp = otp,
                    callback = object : TruvideoSdkCallback<Unit> {

                        override fun onComplete(result: Unit) {
                            callback.onSuccess("OTP Authentication Success")
                        }

                        override fun onError(
                            exception: TruvideoSdkException
                        ) {
                            callback.onFailure(
                                exception.message ?: exception.toString()
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                callback.onFailure(e.message ?: "Unknown error")
            }
        }




        @JvmStatic
        fun waitAuthReady(callback: CoreCallback) {
            try {
                TruvideoSdk.waitAuthReady(
                    callback = object : TruvideoSdkCallback<Unit> {

                        override fun onComplete(result: Unit) {
                            callback.onSuccess("Authentication Ready")
                        }

                        override fun onError(
                            exception: TruvideoSdkException
                        ) {
                            callback.onFailure(
                                exception.message ?: exception.toString()
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                callback.onFailure(e.message ?: "Unknown error")
            }
        }
    }
}