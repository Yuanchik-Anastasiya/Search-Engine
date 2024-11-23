package com.yuanchik.myapplicationbebe.di.modules

import com.yuanchik.myapplicationbebe.data.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/* Здесь у нас будут создаваться инстансы всего того,
что относится к слою Model (то есть данным для всего приложения). */

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRepository() = MainRepository()
}