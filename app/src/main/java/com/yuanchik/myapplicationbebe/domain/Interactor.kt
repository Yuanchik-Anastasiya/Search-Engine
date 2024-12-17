package com.yuanchik.myapplicationbebe.domain

import com.yuanchik.myapplicationbebe.API
import com.yuanchik.myapplicationbebe.data.TmdbApi
import com.yuanchik.myapplicationbebe.data.Entity.TmdbResultsDto
import com.yuanchik.myapplicationbebe.data.MainRepository
import com.yuanchik.myapplicationbebe.data.PreferenceProvider
import com.yuanchik.myapplicationbebe.utils.Converter
import com.yuanchik.myapplicationbebe.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: TmdbApi, private val preferences: PreferenceProvider) {
    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.KEY, "ru-RU", page).enqueue(object : Callback<TmdbResultsDto> {
            override fun onResponse(call: Call<TmdbResultsDto>, response: Response<TmdbResultsDto>) {
                callback.onSuccess(Converter.convertApiListToDtoList(response.body()?.tmdbFilms))
            }

            override fun onFailure(call: Call<TmdbResultsDto>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()
}