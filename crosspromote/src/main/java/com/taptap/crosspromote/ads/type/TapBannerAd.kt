package com.taptap.crosspromote.ads.type

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.taptap.crosspromote.ads.R
import kotlinx.android.synthetic.main.tap_ads_natice_banner.view.*


class TapBannerAd : BaseNative() {

    override var typeAd: String = TYPE_BANNER


    override fun getAdNative(): View? {
        super.getAdNative()
        val layoutInflater = LayoutInflater.from(context)
        val bannerView = layoutInflater.inflate(R.layout.tap_ads_natice_banner, null, false)
        nativeAdDetail?.let{ appDetail ->
            appDetail.appName.let { appName ->
                bannerView.appTitle.let {
                    @SuppressLint("SetTextI18n")
                    it.text = "${appName.take(40)}..."
                }

            }
            appDetail.icon.url.let { appIconUrl ->
                bannerView.appIcon.let {appIconView ->
                    Glide.with(context)
                        .load(appIconUrl)
                        .into(appIconView)
                }

            }

            bannerView.appCta.let {
                it.setOnClickListener {
                    onClick()
                }
            }
            return bannerView
        }

        return null

    }

    override fun loadAd() {
        super.loadAd()
    }
}