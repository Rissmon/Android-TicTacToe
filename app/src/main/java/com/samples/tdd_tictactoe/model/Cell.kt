package com.samples.tdd_tictactoe.model

data class Cell(
    val column: Int,
    val row: Int,
    var state: CellState = Clear
)
