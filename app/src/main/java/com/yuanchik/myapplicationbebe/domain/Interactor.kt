package com.yuanchik.myapplicationbebe.domain

import com.yuanchik.myapplicationbebe.data.MainRepository

class Interactor(val repo: MainRepository) {
    fun getFilmsDB(): List<Film> = repo.filmsDataBase
}