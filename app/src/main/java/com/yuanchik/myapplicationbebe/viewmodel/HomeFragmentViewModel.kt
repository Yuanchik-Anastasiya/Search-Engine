package com.yuanchik.myapplicationbebe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanchik.myapplicationbebe.App
import com.yuanchik.myapplicationbebe.domain.Film
import com.yuanchik.myapplicationbebe.domain.Interactor

class HomeFragmentViewModel :ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    private var interactor: Interactor = App.instance.interactor
    init {

        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}