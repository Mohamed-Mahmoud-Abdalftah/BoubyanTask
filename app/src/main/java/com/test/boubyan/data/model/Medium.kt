package com.test.boubyan.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Medium {
    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("subtype")
    @Expose
    var subtype: String? = null

    @SerializedName("caption")
    @Expose
    var caption: String? = null

    @SerializedName("copyright")
    @Expose
    var copyright: String? = null

    @SerializedName("approved_for_syndication")
    @Expose
    var approvedForSyndication: Int? = null

    @SerializedName("media-metadata")
    @Expose
    var mediaMetadata: List<MediaMetadatum>? = null
}