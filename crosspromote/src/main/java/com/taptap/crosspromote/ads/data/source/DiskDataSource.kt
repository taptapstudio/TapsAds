package com.taptap.crosspromote.ads.data.source

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.taptap.crosspromote.ads.CrossPromote
import com.taptap.crosspromote.ads.data.DataSource
import com.taptap.crosspromote.ads.data.mdoel.DataCache
import com.taptap.crosspromote.ads.model.Campaign
import timber.log.Timber


class DiskDataSource : DataSource {
    private val campaignDataKey = "campaign"
    private val campaignTimeKey = "campaign_time_cache"

    private lateinit var preferences: SharedPreferences

    override fun getCampaign(bundle: String): DataCache? {
        Timber.d("getCampaign->DiskDataSource")
        preferences = CrossPromote.getContext().applicationContext.getSharedPreferences(
            CrossPromote.sharedPreferencesKey,
            Context.MODE_PRIVATE
        )

        val data = preferences.getString(campaignDataKey + "_" + bundle, null)
        if (data.isNullOrBlank()) {
            return null
        }

        val timestamp: Long = preferences.getLong(campaignTimeKey + "_" + bundle, 0)
        return try {
            var campaign: Campaign? = null
            campaign = Gson().fromJson(data, Campaign::class.java)
            DataCache(campaign, timestamp, "Disk")
        } catch (e: Exception) {
            Timber.e(e)

            null
        }


    }

    /**
     * Save data when get response from network
     */
    fun saveCampaign(bundle: String, cache: DataCache) {
        Timber.d("saveCampaign")
        preferences = CrossPromote.getContext().applicationContext.getSharedPreferences(
            CrossPromote.sharedPreferencesKey,
            Context.MODE_PRIVATE
        )
        val dataCampaign: String = Gson().toJson(cache.campaign)
        with(preferences.edit()) {
            putString(campaignDataKey + "_" + bundle, dataCampaign)
            putLong(campaignTimeKey + "_" + bundle, cache.timestamp)
            Timber.d("saveCampaign->bundle")
            apply()
        }
    }
}