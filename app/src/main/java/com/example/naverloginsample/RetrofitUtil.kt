package com.example.naverloginsample

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header

object RetrofitUtil {
    private var retrofit: Retrofit? = null

    fun getService(): API {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!.create(API::class.java)
    }

    interface API {
        @GET("v1/nid/me")
        fun getMe(
            @Header("Authorization") authorization: String
        ): Call<MeResponse>

    }
}