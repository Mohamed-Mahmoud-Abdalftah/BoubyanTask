package com.test.boubyan.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiPopular(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null,

    @SerializedName("num_results")
    @Expose
    var numResults: Int? = null,

    @SerializedName("results")
    @Expose
    var resultData: List<ResultData>? = null
)