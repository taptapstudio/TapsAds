package com.taptap.crosspromote.ads.data

import com.taptap.crosspromote.ads.CrossPromote
import com.taptap.crosspromote.ads.data.mdoel.DataCache
import com.taptap.crosspromote.ads.data.source.DiskDataSource
import com.taptap.crosspromote.ads.data.source.NetworkDataSource
import com.taptap.crosspromote.ads.model.Campaign
import timber.log.Timber

class Repository {

    //    private var memoryDataSource: MemoryDataSource? = null
    private var diskDataSource: DiskDataSource? = null
    private var networkDataSource: NetworkDataSource? = null

    init {
//        memoryDataSource = MemoryDataSource()
        diskDataSource = DiskDataSource()
        networkDataSource = NetworkDataSource()
    }

    fun getAdsByBundle(bundle: String): Campaign? {
        var dataCache: DataCache? = diskDataSource?.getCampaign(bundle)

        return if (dataCache == null) {
            Timber.d("getAdsByBundle null")
            loadFormNetwork(bundle)
        } else {
            if (dataCache.timestamp + CrossPromote.expTime > System.currentTimeMillis()) {
                Timber.d("getAdsByBundle pass")
                dataCache.campaign
            } else {
                Timber.d("getAdsByBundle ex")
                loadFormNetwork(bundle)
            }
        }
    }

    private fun loadFormNetwork(bundle: String): Campaign? {
        Timber.d("loadFormNetwork")
        val dataCache = networkDataSource?.getCampaign(bundle)
        dataCache?.campaign?.let {

            Timber.d("loadFormNetwork->")
            diskDataSource?.saveCampaign(bundle, dataCache)
            return it
        }

        return null
    }
}
