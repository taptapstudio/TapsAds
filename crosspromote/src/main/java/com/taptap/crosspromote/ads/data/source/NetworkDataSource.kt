package com.taptap.crosspromote.ads.data.source

import com.google.gson.GsonBuilder
import com.taptap.crosspromote.ads.BuildConfig
import com.taptap.crosspromote.ads.CrossPromote
import com.taptap.crosspromote.ads.data.DataSource
import com.taptap.crosspromote.ads.data.TapAdsService
import com.taptap.crosspromote.ads.data.mdoel.DataCache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


class NetworkDataSource : DataSource {
    private val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()
    private val tapAdsService: TapAdsService = Retrofit.Builder()
        .baseUrl(CrossPromote.BASEURL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .apply {
                            level = if (BuildConfig.DEBUG)
                                HttpLoggingInterceptor.Level.BODY
                            else
                                HttpLoggingInterceptor.Level.NONE
                        })
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build().create(TapAdsService::class.java)

    override fun getCampaign(bundle: String): DataCache? {
        Timber.d("getCampaign->NetworkDataSource")
        val callCampaign = tapAdsService.getCampaignByBundle(bundle)
        var dataCache: DataCache? = null

        val listCampaign = callCampaign.execute().body()
        listCampaign?.forEach {
            Timber.d(it.toString())
        }
        listCampaign?.get(0)?.let {
            dataCache = DataCache(it, System.currentTimeMillis())
        }
        return dataCache
    }
}