package com.taptap.crosspromote.ads.type

import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.taptap.crosspromote.ads.R
import kotlinx.android.synthetic.main.tap_ads_natice_banner.view.*
import timber.log.Timber


class TapBannerAd : BaseNative() {

    override var typeAd: String = TYPE_BANNER

    override fun getAdNative(): View? {
        super.getAdNative()
        val layoutInflater = LayoutInflater.from(context)
        val bannerView = layoutInflater.inflate(R.layout.tap_ads_natice_banner, null, false)

        bannerView.appTitle.text = nativeAdDetail?.appName?.take(40) + "..."
        Glide.with(context)
            .load(nativeAdDetail?.icon?.url)
            .into(bannerView.appIcon)
        bannerView.appCta.setOnClickListener {
            onClick()
        }

        Timber.d("getAdNative: getAdShowgetAdShow%s", nativeAdDetail.toString())
        return bannerView
    }

    override fun loadAd() {
        super.loadAd()
    }
}