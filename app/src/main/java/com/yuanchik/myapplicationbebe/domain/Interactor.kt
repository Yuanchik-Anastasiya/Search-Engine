package com.yuanchik.myapplicationbebe.domain

import com.yuanchik.myapplicationbebe.API
import com.yuanchik.myapplicationbebe.data.TmdbApi
import com.yuanchik.myapplicationbebe.data.Entity.TmdbResultsDto
import com.yuanchik.myapplicationbebe.data.MainRepository
import com.yuanchik.myapplicationbebe.utils.Converter
import com.yuanchik.myapplicationbebe.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi) {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}