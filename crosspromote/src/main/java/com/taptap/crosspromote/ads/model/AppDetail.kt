package com.taptap.crosspromote.ads.model


import com.google.gson.annotations.SerializedName

data class AppDetail(
    @SerializedName("app_name")
    var appName: String,
    @SerializedName("body_content")
    var bodyContent: String,
    @SerializedName("created_at")
    var createdAt: String,
    @SerializedName("icon")
    var icon: Icon,
    @SerializedName("id")
    var id: Int,
    @SerializedName("link_tracking")
    var linkTracking: String,
    @SerializedName("media")
    var media: Media,
    @SerializedName("rating")
    var rating: Double,
    @SerializedName("short_content")
    var shortContent: String,
    @SerializedName("updated_at")
    var updatedAt: String
)