package com.yuanchik.myapplicationbebe.di.modules

import android.content.Context
import com.yuanchik.myapplicationbebe.data.MainRepository
import com.yuanchik.myapplicationbebe.data.db.DatabaseHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/* Здесь у нас будут создаваться инстансы всего того,
что относится к слою Model (то есть данным для всего приложения). */

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)

    @Provides
    @Singleton
    fun provideRepository(databaseHelper: DatabaseHelper) = MainRepository(databaseHelper)
}