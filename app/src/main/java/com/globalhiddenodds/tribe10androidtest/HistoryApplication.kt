package com.globalhiddenodds.tribe10androidtest

import android.app.Application
import com.globalhiddenodds.tribe10androidtest.data.AppContainer
import com.globalhiddenodds.tribe10androidtest.data.AppDataContainer

class HistoryApplication: Application() {
    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}