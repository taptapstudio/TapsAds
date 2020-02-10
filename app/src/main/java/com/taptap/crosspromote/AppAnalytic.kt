package com.taptap.crosspromote

import android.util.Log
import com.taptap.crosspromote.ads.AnalyticsAd

class AppAnalytic: AnalyticsAd() {

    override fun logRequestSuccessAd(typeAd: String, packageApp: String) {
        super.logRequestSuccessAd(typeAd, packageApp)
        Log.d("AppAnalytic", logParameters.toString())
    }

    override fun logRequestFailedAd(typeAd: String, packageApp: String) {
        super.logRequestFailedAd(typeAd, packageApp)
        Log.d("AppAnalytic", logParameters.toString())
    }

    override fun logImpressionLoggedAd(typeAd: String, packageApp: String) {
        super.logImpressionLoggedAd(typeAd, packageApp)
        Log.d("AppAnalytic", logParameters.toString())
    }

    override fun logClickAd(typeAd: String, packageApp: String) {
        super.logClickAd(typeAd, packageApp)
        Log.d("AppAnalytic", logParameters.toString())
    }
}