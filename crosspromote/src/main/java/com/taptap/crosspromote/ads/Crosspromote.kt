package com.taptap.crosspromote.ads

import android.content.Context
import com.taptap.crosspromote.ads.utils.SingletonHolder

class Crosspromote(
    context: Context
) {

    private var packBundleApp: String

    init {
        packBundleApp = context.packageName
    }

    fun debugApp(bundleApp: String) {
        packBundleApp = bundleApp
    }

    fun setup() {

    }


    companion object : SingletonHolder<Crosspromote, Context>(::Crosspromote)

}