package com.samples.tdd_tictactoe.di

import com.samples.tdd_tictactoe.data.BoardRepository
import com.samples.tdd_tictactoe.data.OnMemoryBoardRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class GameBoardModule {

    @Binds
    abstract fun bindBoardRepository(
        analyticsServiceImpl: OnMemoryBoardRepository
    ): BoardRepository
}