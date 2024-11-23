package com.yuanchik.myapplicationbebe

import android.app.Application
import com.yuanchik.myapplicationbebe.di.AppComponent
import com.yuanchik.myapplicationbebe.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        //Создаем компонент
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}