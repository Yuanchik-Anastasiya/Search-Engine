package com.yuanchik.myapplicationbebe.di.modules

import com.yuanchik.myapplicationbebe.data.MainRepository
import com.yuanchik.myapplicationbebe.data.TmdbApi
import com.yuanchik.myapplicationbebe.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/* Тут у нас будет создаваться наш Interactor,
честно сказать, это спорный вопрос, где нужно его создавать. */

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) = Interactor(repo = repository, retrofitService = tmdbApi)
}