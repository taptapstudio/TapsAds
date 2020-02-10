package com.taptap.crosspromote.ads.data.mdoel

import com.taptap.crosspromote.ads.model.Campaign

data class DataCache(
    var campaign: Campaign?,
    val timestamp:Long,
    var source:String = "network"
)