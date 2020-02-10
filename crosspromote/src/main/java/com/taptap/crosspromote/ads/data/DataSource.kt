package com.taptap.crosspromote.ads.data

import com.taptap.crosspromote.ads.data.mdoel.DataCache

interface DataSource {
    fun getCampaign(bundle: String): DataCache?
}