package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.common.MainCoroutineRule
import com.samples.tdd_tictactoe.common.cleanBoardCells
import com.samples.tdd_tictactoe.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WinnerCheckHelperTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val winnerCheckHelper = WinnerCheckHelper(testDispatcher)

    @Test
    fun checkForWinner_returnsXPlayerIfFirstRowHasXSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(0, Cell(0, 0, XSelected))
                set(3, Cell(0, 1, OSelected))
                set(1, Cell(1, 0, XSelected))
                set(4, Cell(1, 1, OSelected))
                set(2, Cell(2, 0, XSelected))
            })
            assertEquals(XPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 0, XSelected)))
        }

    @Test
    fun checkForWinner_returnOPlayerIfFirstRowHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(4, Cell(1, 1, XSelected))
                set(0, Cell(0, 0, OSelected))
                set(7, Cell(1, 2, XSelected))
                set(1, Cell(1, 0, OSelected))
                set(8, Cell(2, 2, XSelected))
                set(2, Cell(2, 0, OSelected))
            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 0, OSelected)))
        }

    @Test
    fun checkForWinner_returnsXPlayerIfSecondRowHasXSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(3, Cell(0, 1, XSelected))
                set(1, Cell(1, 0, OSelected))
                set(4, Cell(1, 1, XSelected))
                set(7, Cell(1, 2, OSelected))
                set(5, Cell(2, 1, XSelected))
            })

            assertEquals(XPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 1, XSelected)))
        }

    @Test
    fun checkForWinner_returnOPlayerIfSecondRowHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(0, Cell(0, 0, XSelected))
                set(3, Cell(0, 1, OSelected))
                set(1, Cell(1, 0, XSelected))
                set(4, Cell(1, 1, OSelected))
                set(8, Cell(2, 2, XSelected))
                set(5, Cell(2, 1, OSelected))
            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 1, OSelected)))
        }

    @Test
    fun checkForWinner_returnsOPlayerIfRightDiagonalHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(1, Cell(1, 0, XSelected))
                set(0, Cell(0, 0, OSelected))
                set(3, Cell(0, 1, XSelected))
                set(4, Cell(1, 1, OSelected))
                set(5, Cell(2, 1, XSelected))
                set(8, Cell(2, 2, OSelected))

            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 2, OSelected)))
        }

    @Test
    fun checkForWinner_returnsOPlayerIfLeftDiagonalHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(1, Cell(1, 0, XSelected))
                set(2, Cell(2, 0, OSelected))
                set(3, Cell(0, 1, XSelected))
                set(4, Cell(1, 1, OSelected))
                set(5, Cell(2, 1, XSelected))
                set(6, Cell(0, 2, OSelected))

            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(0, 2, OSelected)))
        }

}