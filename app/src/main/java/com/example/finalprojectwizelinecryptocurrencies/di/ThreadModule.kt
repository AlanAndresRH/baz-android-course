package com.example.finalprojectwizelinecryptocurrencies.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ThreadModule {

    @Provides
    @Singleton
    @MainScheduler
    fun providesMainScheduler(): Scheduler =  AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    @IODispatchers
    fun providesIoDispatchers(): CoroutineDispatcher = Dispatchers.IO
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainScheduler

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IODispatchers