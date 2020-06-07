package com.example.naverloginsample

import android.app.Activity
import android.util.Log
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import retrofit2.Call
import retrofit2.Response
import java.lang.ref.WeakReference

class MyHandler(activity: Activity) : OAuthLoginHandler() {
    private val weakReference: WeakReference<Activity> by lazy { WeakReference(activity) }

    private val oAuthLoginModule by lazy {
        OAuthLogin.getInstance().apply {
            init(
                weakReference.get(),
                OAUTH_CLIENT_ID,
                OAUTH_CLIENT_SECRET,
                OAUTH_CLIENT_NAME
            )
        }
    }

    fun startOauthLoginActivity() {
        oAuthLoginModule.startOauthLoginActivity(weakReference.get(), this)
    }

    override fun run(success: Boolean) {
        if (success) {
            val accessToken = oAuthLoginModule.getAccessToken(weakReference.get())

            RetrofitUtil.getService().getMe("Bearer $accessToken")
                .enqueue(object : retrofit2.Callback<MeResponse> {
                    override fun onFailure(call: Call<MeResponse>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(
                        call: Call<MeResponse>,
                        response: Response<MeResponse>
                    ) {
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                val result = response.body()!!
                                if (result.resultcode == "00" && result.message == "success") {
                                    Log.i(TAG, result.response.toString())
                                }
                            }
                        }
                    }
                })
        } else {
            val errorCode = oAuthLoginModule.getLastErrorCode(weakReference.get())
            val errorDesc = oAuthLoginModule.getLastErrorDesc(weakReference.get())
            Log.i(TAG, "errorCode: $errorCode, errorDesc: $errorDesc")
        }
    }

    companion object {
        private const val TAG = "NaverApiResponse"

        private const val OAUTH_CLIENT_ID = "OAUTH_CLIENT_ID"
        private const val OAUTH_CLIENT_SECRET = "OAUTH_CLIENT_SECRET"
        private const val OAUTH_CLIENT_NAME = "OAUTH_CLIENT_NAME"
    }
}