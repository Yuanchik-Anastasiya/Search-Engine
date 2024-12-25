package com.yuanchik.myapplicationbebe.di.modules

import android.content.Context
import androidx.room.Room
import com.yuanchik.myapplicationbebe.data.DAO.AppDatabase
import com.yuanchik.myapplicationbebe.data.DAO.FilmDAO
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
    fun provideFilmDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDAO) = MainRepository(filmDao)
}