package com.taptap.crosspromote.ads.model


import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("ext")
    var ext: String,
    @SerializedName("hash")
    var hash: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("mime")
    var mime: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("provider")
    var provider: String,
    @SerializedName("provider_metadata")
    var providerMetadata: Any?,
    @SerializedName("sha256")
    var sha256: String,
    @SerializedName("size")
    var size: Double,
    @SerializedName("updated_at")
    var updatedAt: String,
    @SerializedName("url")
    var url: String
)