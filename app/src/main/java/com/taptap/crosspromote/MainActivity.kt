package com.taptap.crosspromote

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.taptap.crosspromote.ads.CrossPromote
import com.taptap.crosspromote.ads.listeners.NativeAdListener
import com.taptap.crosspromote.ads.type.banner.BannerAd
import com.taptap.crosspromote.ads.type.native.NativeAd
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
        val banner: BannerAd = BannerAd()
        banner.nativeAdListener = object : NativeAdListener {

            override fun onRequestSuccessAd() {
                Log.d(localClassName, "onRequestSuccessAd")
                var view: View? = banner.getAdNative()
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
        banner.loadAd()
    }

    private fun showNative() {
        Log.d(localClassName, "showBanner")
        val nativeAd: NativeAd = NativeAd()
        nativeAd.nativeAdListener = object : NativeAdListener {

            override fun onRequestSuccessAd() {
                Log.d(localClassName, "onRequestSuccessAd")
                var view: View? = nativeAd.getAdNative()
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
        nativeAd.loadAd()
    }
}
