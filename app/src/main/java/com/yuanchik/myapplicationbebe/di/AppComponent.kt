package com.yuanchik.myapplicationbebe.di

import com.yuanchik.myapplicationbebe.di.modules.DatabaseModule
import com.yuanchik.myapplicationbebe.di.modules.DomainModule
import com.yuanchik.myapplicationbebe.di.modules.RemoteModule
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
    //метод для того, чтобы появилась внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}