package com.taptap.crosspromote.ads

import android.content.Context
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.taptap.crosspromote.ads.data.Repository
import com.taptap.crosspromote.ads.model.AppDetail
import com.taptap.crosspromote.ads.model.Campaign
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import timber.log.Timber
import timber.log.Timber.DebugTree
import java.util.concurrent.TimeUnit


object CrossPromote {
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    var BASEURL: String = "https://tapads.taptapstudio.online"
    const val sharedPreferencesKey = "cross_promote"
    private lateinit var mContext: Context
    var analyticsAdProvider: AnalyticsAd? = null

    private var packBundleApp: String = ""
    private var repository: Repository? = null

    var expTime: Long = TimeUnit.HOURS.toMillis(5)

    var isDebug = false
    var isError = false
    var isLoaded = false
    var isLoading = false

    private var listAppDetails: MutableList<AppDetail> = mutableListOf()

    fun getApp() = packBundleApp

    fun debugApp(bundleApp: String) {
        isDebug = true
        packBundleApp = bundleApp
    }

    fun setAnalytics(analyticsAd: AnalyticsAd) {
        analyticsAdProvider = analyticsAd
    }

    fun setup(context: Context) {
        if (BuildConfig.DEBUG) {
            expTime = TimeUnit.SECONDS.toMillis(50)
            Timber.plant(DebugTree())
            Timber.tag("CrossPromote")
        }
        Timber.d("setup")
        mContext = context
        repository = Repository()

        if (!isDebug) {
            packBundleApp = mContext.packageName

        }
        isLoading = true
        scope.launch(Main) {
            withContext(IO) {
                try {
                    val campaign = repository?.getAdsByBundle(packBundleApp)

                    Timber.d("""campaign ${campaign.toString()}""")
                    if (campaign != null) {
                        isError = false
                        isLoading = false
                        isLoaded = true
                        doDataNative(campaign)
                    } else {
                        isError = true
                        isLoaded = true
                        isLoading = false
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }

        }
    }

    private fun doDataNative(campaign: Campaign) {
        Timber.d(campaign.toString())

        campaign.appDetails.forEach {
            listAppDetails.add(it)
        }
        loadImageToCache()
    }

    private fun loadImageToCache() {
        scope.launch(Main) {

            val requestOptions =
                RequestOptions().transform(FitCenter(), RoundedCorners(15)).diskCacheStrategy(
                    DiskCacheStrategy.ALL
                )
            withContext(IO) {
                listAppDetails.forEach {

                    try {
                        it.icon.url.let { url ->
                            Glide.with(mContext)
                                .asBitmap()
                                .load(url)
                                .apply(requestOptions)
                                .submit(64, 64)
                        }
                    }catch (e:Exception){
                        Timber.e(e)
                    }

                    try {
                        it.media.url.let { url ->
                            Glide.with(mContext)
                                .asBitmap()
                                .load(url)
                                .apply(requestOptions)
                                .submit(500,244)
                        }
                    }catch (e:Exception){
                        Timber.e(e)
                    }
                }
            }
        }
    }

    fun getAdShow(): AppDetail? {
        return if (listAppDetails.isNotEmpty()) {
            listAppDetails.random()
        } else {
            null
        }
    }

    fun getContext(): Context = mContext

    fun isSetup(): Boolean {
        if (::mContext.isInitialized) {
            return true
        }
        return false
    }
}