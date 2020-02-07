package com.taptap.crosspromote.ads.data

import com.taptap.crosspromote.ads.model.AppDetail

object DataManager {

    private var instance: DataManager? = null
    private var listAppDetails: MutableList<AppDetail> = mutableListOf()

    init {
        setupData()
    }

    private fun setupData() {

    }
}