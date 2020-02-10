package com.taptap.crosspromote.ads.type

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.annotation.StringDef
import com.taptap.crosspromote.ads.AnalyticsAd
import com.taptap.crosspromote.ads.CrossPromote
import com.taptap.crosspromote.ads.listeners.NativeAdListener
import com.taptap.crosspromote.ads.model.AppDetail
import com.taptap.crosspromote.ads.utils.BuilderLinkTracking
import timber.log.Timber


abstract class BaseNative {
    var analyticsAd: AnalyticsAd? = null
    var nativeAdListener: NativeAdListener? = null
    var isLoaded = false
    var isClicked: Boolean = false
    var isError = false
    var context: Context

    open var typeAd: String = TYPE_BANNER
    var nativeAdDetail: AppDetail? = null

    init {
        analyticsAd = CrossPromote.analyticsAdProvider
        context = CrossPromote.getContext()
    }

    @Throws(Exception::class)
    open fun getAdNative(): View? {
        Timber.tag("CrossNative").d("getAdNative")
        return View(context)
    }

    @Throws(Exception::class)
    open fun loadAd() {
        Timber.tag("CrossNative").d("loadAd")
        onRequest()
        var appDetailAd = CrossPromote.getAdShow()

        if (appDetailAd == null) {
            onRequestFailed()
        } else {
            nativeAdDetail = appDetailAd
            onRequestSuccess()
        }
    }


    fun onRequest() {
        Timber.tag("CrossNative").d("onRequest")
        isLoaded = false
        isClicked = false
        isError = false

        nativeAdDetail?.let {
            nativeAdDetail?.packageApp?.let { it1 -> analyticsAd?.logRequestAd(it1, typeAd) }
        }
        nativeAdListener?.onRequest()
    }

    fun onRequestSuccess() {
        Timber.tag("CrossNative").d("onRequestSuccess")
        isLoaded = true
        isClicked = false


        nativeAdDetail?.let {
            nativeAdDetail?.packageApp?.let { it1 -> analyticsAd?.logRequestSuccessAd(it1, typeAd) }
        }
        nativeAdListener?.onRequestSuccessAd()
    }

    fun onRequestFailed() {
        Timber.tag("CrossNative").d("onRequestFailed")
        isError = true
        isLoaded = true
        isClicked = false

        nativeAdDetail?.let {
            nativeAdDetail?.packageApp?.let { it1 -> analyticsAd?.logRequestFailedAd(it1, typeAd) }
        }
        nativeAdListener?.onRequestFailedAd()
    }

    fun onImpressionLogged() {
        Timber.tag("CrossNative").d("onImpressionLogged")

        nativeAdDetail?.let {
            nativeAdDetail?.packageApp?.let { it1 ->
                analyticsAd?.logImpressionLoggedAd(
                    it1,
                    typeAd
                )
            }
        }
        nativeAdListener?.onImpressionLoggedAd()
    }

    fun onClick() {
        Timber.tag("CrossNative").d("onClick")

        nativeAdDetail?.let {
            nativeAdDetail?.packageApp?.let { it1 -> analyticsAd?.logClickAd(it1, typeAd) }
        }
        nativeAdListener?.onClickAd()

        nativeAdDetail?.packageApp?.let {

            val urlTracking: String? =
                BuilderLinkTracking(
                    applicationId = it,
                    campaignSource = "TapAds",
                    campaignMedium = typeAd,
                    campaignName = "product"
                ).buildUrlTracking()

            urlTracking?.let {
                Timber.d(it)
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlTracking))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                } catch (e: java.lang.Exception) {
                    Timber.e(e)
                }

            }
        }

    }

    fun onClickAdChoice(){

    }

    companion object {
        const val TYPE_BANNER = "banner"
        const val TYPE_NATIVE = "native"
        const val TYPE_INTERSTITIAL = "INTERSTITIAL"

        @Retention(AnnotationRetention.SOURCE)
        @StringDef(TYPE_BANNER, TYPE_NATIVE, TYPE_INTERSTITIAL)
        annotation class TypeAd
    }
}