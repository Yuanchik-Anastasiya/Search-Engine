package com.yuanchik.myapplicationbebe.data

import androidx.lifecycle.LiveData
import com.yuanchik.myapplicationbebe.data.DAO.FilmDAO
import com.yuanchik.myapplicationbebe.data.Entity.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDAO) {
    fun putToDb(films: List<Film>) {
        //Запросы в БД должны быть в отдельном потоке
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<List<Film>> =
        filmDao.getCachedFilms()
}