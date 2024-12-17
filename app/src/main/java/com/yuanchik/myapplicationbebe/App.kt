package com.yuanchik.myapplicationbebe

import android.app.Application
import com.yuanchik.myapplicationbebe.di.AppComponent
import com.yuanchik.myapplicationbebe.di.DaggerAppComponent
import com.yuanchik.myapplicationbebe.di.modules.DatabaseModule
import com.yuanchik.myapplicationbebe.di.modules.DomainModule
import com.yuanchik.myapplicationbebe.di.modules.RemoteModule

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}