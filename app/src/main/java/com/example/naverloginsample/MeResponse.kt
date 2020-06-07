package com.example.naverloginsample

import com.google.gson.annotations.SerializedName

data class MeResponse(

	@field:SerializedName("response")
	val response: Response? = null,

	@field:SerializedName("resultcode")
	val resultcode: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Response(

	@field:SerializedName("birthday")
	val birthday: String? = null,

	@field:SerializedName("profile_image")
	val profileImage: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("nickname")
	val nickname: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("age")
	val age: String? = null
)
