package com.yuanchik.myapplicationbebe.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yuanchik.myapplicationbebe.App
import com.yuanchik.myapplicationbebe.domain.Film
import com.yuanchik.myapplicationbebe.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel :ViewModel() {
    val filmsListLiveData = MutableLiveData<List<Film>>()
    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(films: List<Film>) {
                filmsListLiveData.postValue(films)
            }

            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(films: List<Film>)
        fun onFailure()
    }
}