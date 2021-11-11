package com.samples.tdd_tictactoe.view.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samples.tdd_tictactoe.model.PlayerData
import dagger.assisted.AssistedFactory


@AssistedFactory
interface GameViewModelAssistedFactory {
    fun create(playerData: PlayerData): GameViewModel
}

fun provideFactory(
    assistedFactory: GameViewModelAssistedFactory,
    playerData: PlayerData
): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(playerData) as T
    }
}