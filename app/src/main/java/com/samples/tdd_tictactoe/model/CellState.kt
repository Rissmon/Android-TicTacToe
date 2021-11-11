package com.samples.tdd_tictactoe.model

sealed interface CellState

object Clear : CellState

object XSelected : CellState

object OSelected : CellState
