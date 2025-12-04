package com.zulfadar.technicaltest.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetQuoteResponse(

	@field:SerializedName("quote")
	val quote: Quote? = null,

	@field:SerializedName("qotd_date")
	val qotdDate: String? = null
)

data class Quote(

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("body")
	val body: String? = null,
)
