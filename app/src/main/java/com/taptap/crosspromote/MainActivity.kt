package com.taptap.crosspromote

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.taptap.crosspromote.ads.CrossPromote
import com.taptap.crosspromote.ads.listeners.NativeAdListener
import com.taptap.crosspromote.ads.type.TapBannerAd
import com.taptap.crosspromote.ads.type.TapNativeAd
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CrossPromote.debugApp("com.taptap.collagemaker.photolayoutfree")

        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                showBanner()
                showNative()
            }
        }.start()


        Log.d("MainActivity", CrossPromote.getAdShow().toString())
    }

    private fun showBanner() {
        Log.d(localClassName, "showBanner")
        val tapBanner: TapBannerAd =
            TapBannerAd()
        tapBanner.nativeAdListener = object : NativeAdListener {

            override fun onRequestSuccessAd() {
                Log.d(localClassName, "onRequestSuccessAd")
                var view: View? = tapBanner.getAdNative()
                view?.let {
                    adViewBanner.addView(view)
                }
            }

            override fun onImpressionLoggedAd() {
                super.onImpressionLoggedAd()
                Log.d(localClassName, "onImpressionLoggedAd")
            }

            override fun onClickAd() {
                super.onClickAd()
                Log.d(localClassName, "onClickAd")
            }
        }
        tapBanner.loadAd()
    }

    private fun showNative() {
        Log.d(localClassName, "showBanner")
        val tapNativeAd: TapNativeAd =
            TapNativeAd()
        tapNativeAd.nativeAdListener = object : NativeAdListener {

            override fun onRequestSuccessAd() {
                Log.d(localClassName, "onRequestSuccessAd")
                var view: View? = tapNativeAd.getAdNative()
                view?.let {
                    adViewNative.addView(view)
                }
            }

            override fun onImpressionLoggedAd() {
                super.onImpressionLoggedAd()
                Log.d(localClassName, "onImpressionLoggedAd")
            }

            override fun onClickAd() {
                super.onClickAd()
                Log.d(localClassName, "onClickAd")
            }
        }
        tapNativeAd.loadAd()
    }
}
