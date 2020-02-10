package com.taptap.crosspromote.ads

import android.os.Bundle
import androidx.core.os.bundleOf
import com.taptap.crosspromote.ads.type.BaseNative

open class AnalyticsAd protected constructor() {
    var logParameters:Bundle = bundleOf(
        Pair("form_app", CrossPromote.getApp())
    )


    open fun logRequestAd(@BaseNative.Companion.TypeAd typeAd:String, packageApp: String) {
        logParameters.putString("to_app", packageApp)
        logParameters.putString("type_ad", typeAd)
    }

    open fun logRequestSuccessAd(@BaseNative.Companion.TypeAd typeAd:String, packageApp: String) {
        logParameters.putString("to_app", packageApp)
        logParameters.putString("type_ad", typeAd)
    }

    open fun logRequestFailedAd(@BaseNative.Companion.TypeAd typeAd:String, packageApp: String){
        logParameters.putString("to_app", packageApp)
        logParameters.putString("type_ad", typeAd)
    }

    open fun logImpressionLoggedAd(@BaseNative.Companion.TypeAd typeAd:String, packageApp: String){
        logParameters.putString("to_app", packageApp)
        logParameters.putString("type_ad", typeAd)
    }

    open fun logClickAd(@BaseNative.Companion.TypeAd typeAd:String, packageApp: String){
        logParameters.putString("to_app", packageApp)
        logParameters.putString("type_ad", typeAd)
    }

    companion object {
        protected var INSTANCE: AnalyticsAd? = null
        val instance: AnalyticsAd?
            get() {
                if (INSTANCE == null) {
                    INSTANCE =
                        AnalyticsAd()
                }
                return INSTANCE
            }
    }


}
