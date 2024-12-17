package com.yuanchik.myapplicationbebe.di

import com.yuanchik.myapplicationbebe.di.modules.DatabaseModule
import com.yuanchik.myapplicationbebe.di.modules.DomainModule
import com.yuanchik.myapplicationbebe.di.modules.RemoteModule
import com.yuanchik.myapplicationbebe.di.modules.SettingsFragmentViewModel
import com.yuanchik.myapplicationbebe.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    //Внедряем все модули, нужные для этого компонента
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
    fun inject(settingsFragmentViewModel: SettingsFragmentViewModel)
}