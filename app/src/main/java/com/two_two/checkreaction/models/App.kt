package com.two_two.checkreaction.models

import android.app.Application

/**
 * Created by Dmitry Borodin on 27.01.2016.
 */
class App : Application() {
    var localData: LocalData? = null
        private set

    override fun onCreate() {App
        super.onCreate()
        sInstance = this
//        if (!BuildConfig.DEBUG) {
//            Fabric.with(this.applicationContext, Crashlytics())
//        }
        localData = LocalData(this.applicationContext)
    }

    companion object {
        private var sInstance: App? = null
        @JvmStatic
        val instance: App
            get() = sInstance!!
    }
}
