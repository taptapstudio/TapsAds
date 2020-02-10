package com.taptap.crosspromote.ads.data

import com.taptap.crosspromote.ads.model.Campaign
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TapAdsService {

    @GET("campaigns/")
    fun getCampaignByBundle(@Query("bundle") bundle:String): Call<List<Campaign>>
}