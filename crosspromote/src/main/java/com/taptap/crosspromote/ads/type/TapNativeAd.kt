package com.taptap.crosspromote.ads.type

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import com.bumptech.glide.Glide
import com.taptap.crosspromote.ads.R
import kotlinx.android.synthetic.main.tap_ads_native.view.*
import timber.log.Timber

class TapNativeAd : BaseNative() {

    override var typeAd: String = TYPE_NATIVE

    override fun getAdNative(): View? {
        super.getAdNative()
        val layoutInflater = LayoutInflater.from(context)
        val nativeView = layoutInflater.inflate(R.layout.tap_ads_native, null, false)
        nativeAdDetail?.let { appDetail ->

            appDetail.appName.let { appName ->
                @SuppressLint("SetTextI18n")
                nativeView.appTitle.text = "${appName.take(40)}..."
            }

            appDetail.icon.url.let { appIconUrl ->
                nativeView.appIconNative.let {appIconView ->
                    Glide.with(context)
                        .load(appIconUrl)
                        .centerCrop()
                        .into(appIconView)

                }
            }

            appDetail.media.url.let { appMediaUrl ->
                nativeView.media.let {appMediaView ->
                    Glide.with(context)
                        .load(appMediaUrl)
                        .centerCrop()
                        .into(appMediaView)
                }
            }
            appDetail.shortContent.let { appShortContent ->
                nativeView.appShortDescription.let {appShortDescriptionTextView ->
                    @SuppressLint("SetTextI18n")
                    appShortDescriptionTextView.text = "${appShortContent.take(115)}..."
                }
            }
            nativeView.appCta.let {appCtaButton ->
                appCtaButton.setOnClickListener {
                    onClick()
                }
            }

            Timber.d("getAdNative: getAdShowgetAdShow%s", nativeAdDetail.toString())
            return nativeView
        }
        return null
    }

    override fun loadAd() {
        super.loadAd()
    }
}