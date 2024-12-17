package com.yuanchik.myapplicationbebe.di.modules

import android.content.Context
import com.yuanchik.myapplicationbebe.data.MainRepository
import com.yuanchik.myapplicationbebe.data.PreferenceProvider
import com.yuanchik.myapplicationbebe.data.TmdbApi
import com.yuanchik.myapplicationbebe.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/* Тут у нас будет создаваться наш Interactor,
честно сказать, это спорный вопрос, где нужно его создавать. */

@Module
class DomainModule(val context: Context) {
    //Нам нужно контекст как-то провайдить, поэтому создаем такой метод
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    //Создаем экземпляр SharedPreferences
    fun providePreferences(context: Context) = PreferenceProvider(context)

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi, preferenceProvider: PreferenceProvider) = Interactor(repo = repository, retrofitService = tmdbApi, preferences = preferenceProvider)
}