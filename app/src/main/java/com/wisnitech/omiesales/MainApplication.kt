package com.wisnitech.omiesales

import android.app.Application
import com.wisnitech.omiesales.data.di.dataModule
import com.wisnitech.omiesales.ui.di.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(dataModule, uiModules)
        }

    }
}