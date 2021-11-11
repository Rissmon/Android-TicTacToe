package com.samples.tdd_tictactoe.view.entry

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test

class PlayerEntryViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private val playerEntryViewModel = PlayerEntryViewModel()


    @Test
    fun isPlayerFieldNullOrEmpty_returnFalseIfThereIsValidPlayerNames() {
        playerEntryViewModel.playerX.value = "Player1"
        playerEntryViewModel.playerO.value = "Player2"
        assertFalse(playerEntryViewModel.isPlayerFieldNullOrEmpty)
    }
}