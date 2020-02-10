package com.taptap.crosspromote.ads.utils

import java.net.URLEncoder

data class BuilderLinkTracking(
    private val applicationId: String,
    private val campaignSource: String,
    private val campaignMedium: String = "",
    private val campaignTerm: String = "",
    private val campaignContent: String = "",
    private val campaignName: String = ""
) {
    var baseUrl = "https://play.google.com/store/apps/details?id="
    var urlTracking = ""


    fun buildUrlTracking(): String? {
        if (applicationId.isNotBlank()) {
            urlTracking += applicationId
            if (campaignSource.isNotBlank()) {
                var referrer = "&referrer="
                referrer += "utm_source=$campaignSource"
                if (campaignMedium.isNotBlank()) referrer += "&utm_medium=$campaignMedium"
                if (campaignTerm.isNotBlank()) referrer += "&utm_term=$campaignTerm"
                if (campaignContent.isNotBlank()) referrer += "&utm_content=$campaignContent"
                if (campaignName.isNotBlank()) referrer += "&utm_campaign=$campaignName"
                urlTracking += referrer
            }
        }

        return baseUrl + urlTracking
    }
}