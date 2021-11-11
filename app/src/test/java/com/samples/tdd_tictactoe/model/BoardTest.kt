package com.samples.tdd_tictactoe.model

import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Test

class BoardTest {

    @Test
    fun isCompleted_returnsFalseIfAllOfTheCellsHaveClearState() {
        val board = Board(
            listOf(
                Cell(0, 0, Clear),
                Cell(0, 1, Clear),
                Cell(0, 2, Clear),
                Cell(1, 0, Clear),
                Cell(1, 1, Clear),
                Cell(1, 2, Clear),
                Cell(2, 0, Clear),
                Cell(2, 1, Clear),
                Cell(2, 2, Clear)
            )
        )
        assertFalse(board.isCompleted())
    }

    @Test
    fun isCompleted_returnsFalseIfAnyOneOfTheCellHaveClearState() {
        val board = Board(
            listOf(
                Cell(0, 0, Clear),
                Cell(0, 1, OSelected),
                Cell(0, 2, XSelected),
                Cell(1, 0, OSelected),
                Cell(1, 1, XSelected),
                Cell(1, 2, OSelected),
                Cell(2, 0, XSelected),
                Cell(2, 1, OSelected),
                Cell(2, 2, XSelected)
            )
        )
        assertFalse(board.isCompleted())
    }

    @Test
    fun isCompleted_returnsTrueIfNoClearLeft() {
        val board = Board(
            listOf(
                Cell(0, 0, XSelected),
                Cell(0, 1, OSelected),
                Cell(0, 2, XSelected),
                Cell(1, 0, OSelected),
                Cell(1, 1, XSelected),
                Cell(1, 2, OSelected),
                Cell(2, 0, XSelected),
                Cell(2, 1, OSelected),
                Cell(2, 2, XSelected)
            )
        )
        Assert.assertTrue(board.isCompleted())
    }

}