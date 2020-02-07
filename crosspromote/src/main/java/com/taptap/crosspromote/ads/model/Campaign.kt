package com.taptap.crosspromote.ads.model


import com.google.gson.annotations.SerializedName

data class Campaign(
    @SerializedName("app_details")
    var appDetails: List<AppDetail>,
    @SerializedName("bundle")
    var bundle: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("published")
    var published: Boolean,
    @SerializedName("updated_at")
    var updatedAt: String
)