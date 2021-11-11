package com.samples.tdd_tictactoe.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Qualifier
annotation class BackgroundDispatcher

@Qualifier
annotation class BoardSize

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @BackgroundDispatcher
    fun provideBackgroundDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @BoardSize
    fun provideBoardSize(): Int = 3

}


