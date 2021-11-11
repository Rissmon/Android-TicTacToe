package com.samples.tdd_tictactoe.model

sealed class GameState {
    object Ongoing : GameState()
    object Draw : GameState()
    object Winner : GameState()
}
