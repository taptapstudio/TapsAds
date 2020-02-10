package com.taptap.crosspromote.ads.listeners

interface NativeAdListener {

    fun onRequest() {}

    fun onRequestSuccessAd() {}

    fun onRequestFailedAd() {}

    fun onImpressionLoggedAd() {}

    fun onClickAd() {}
}