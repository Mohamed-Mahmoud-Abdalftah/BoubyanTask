package com.test.boubyan.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MediaMetadatum {
    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("format")
    @Expose
    var format: String? = null

    @SerializedName("height")
    @Expose
    var height: Int? = null

    @SerializedName("width")
    @Expose
    var width: Int? = null
}