package com.taptap.crosspromote.ads.type.native

import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.taptap.crosspromote.ads.R
import com.taptap.crosspromote.ads.type.BaseNative
import kotlinx.android.synthetic.main.tap_ads_natice_banner.view.appCta
import kotlinx.android.synthetic.main.tap_ads_natice_banner.view.appTitle
import kotlinx.android.synthetic.main.tap_ads_native.view.*
import timber.log.Timber

class NativeAd : BaseNative() {

    override var typeAd: String = TYPE_NATIVE

    override fun getAdNative(): View? {
        super.getAdNative()
        val layoutInflater = LayoutInflater.from(context)
        val nativeView = layoutInflater.inflate(R.layout.tap_ads_native, null, false)

        nativeView.appTitle.text = nativeAdDetail?.appName?.take(40) + "..."


        Glide.with(context)
            .load(nativeAdDetail?.media?.url)
            .centerCrop()
            .into(nativeView.media)

        Glide.with(context)
            .load(nativeAdDetail?.icon?.url)
            .into(nativeView.appIconNative)


        nativeView.appShortDescription.text = nativeAdDetail?.appName?.take(115) + "..."


        nativeView.appCta.setOnClickListener {
            onClick()
        }

        Timber.d("getAdNative: getAdShowgetAdShow%s", nativeAdDetail.toString())
        return nativeView
    }
}