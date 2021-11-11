package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.common.MainCoroutineRule
import com.samples.tdd_tictactoe.common.cleanBoardCells
import com.samples.tdd_tictactoe.model.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
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
    fun checkForWinner_returnNullIfGameResultIsDraw() =
        runBlockingTest {
            val board = Board(
                cells = listOf(
                    Cell(0, 0, XSelected),
                    Cell(0, 1, OSelected),
                    Cell(0, 2, XSelected),
                    Cell(1, 0, OSelected),
                    Cell(1, 1, XSelected),
                    Cell(1, 2, OSelected),
                    Cell(2, 1, XSelected),
                    Cell(2, 2, OSelected),
                    Cell(2, 0, XSelected),
                )
            )
            Assert.assertNull(winnerCheckHelper.checkForWinner(board, Cell(2, 2, XSelected)))
        }

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
    fun checkForWinner_returnsXPlayerIfThirdRowHasXSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(6, Cell(0, 2, XSelected))
                set(0, Cell(0, 0, OSelected))
                set(7, Cell(1, 2, XSelected))
                set(3, Cell(0, 1, OSelected))
                set(8, Cell(2, 2, XSelected))
            })
            assertEquals(XPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 2, XSelected)))
        }

    @Test
    fun checkForWinner_returnOPlayerIfThirdRowHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(0, Cell(0, 0, XSelected))
                set(6, Cell(0, 2, OSelected))
                set(1, Cell(1, 0, XSelected))
                set(7, Cell(1, 2, OSelected))
                set(4, Cell(1, 1, XSelected))
                set(8, Cell(2, 2, OSelected))
            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 2, OSelected)))
        }

    @Test
    fun checkForWinner_returnXPlayerIfFirstColumnHasXSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(0, Cell(0, 0, XSelected))
                set(4, Cell(1, 1, OSelected))
                set(3, Cell(0, 1, XSelected))
                set(7, Cell(1, 2, OSelected))
                set(6, Cell(0, 2, XSelected))
            })
            assertEquals(XPlayer, winnerCheckHelper.checkForWinner(board, Cell(0, 2, XSelected)))
        }

    @Test
    fun checkForWinner_returnsXPlayerIfSecondColumnHasXSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(1, Cell(1, 0, XSelected))
                set(0, Cell(0, 0, OSelected))
                set(4, Cell(1, 1, XSelected))
                set(3, Cell(0, 1, OSelected))
                set(7, Cell(1, 2, XSelected))
            })

            assertEquals(XPlayer, winnerCheckHelper.checkForWinner(board, Cell(1, 2, XSelected)))
        }

    @Test
    fun checkForWinner_returnsXPlayerIfThirdColumnHasXSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(2, Cell(2, 0, XSelected))
                set(0, Cell(0, 0, OSelected))
                set(5, Cell(2, 1, XSelected))
                set(3, Cell(0, 1, OSelected))
                set(8, Cell(2, 2, XSelected))
            })
            assertEquals(XPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 2, XSelected)))
        }

    @Test
    fun checkForWinner_returnOPlayerIfFirstColumnHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(1, Cell(1, 0, XSelected))
                set(0, Cell(0, 0, OSelected))
                set(7, Cell(1, 2, XSelected))
                set(3, Cell(0, 1, OSelected))
                set(2, Cell(2, 0, XSelected))
                set(6, Cell(0, 2, OSelected))
            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(0, 2, OSelected)))
        }

    @Test
    fun checkForWinner_returnOPlayerIfSecondColumnHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(0, Cell(0, 0, XSelected))
                set(1, Cell(1, 0, OSelected))
                set(6, Cell(0, 2, XSelected))
                set(4, Cell(1, 1, OSelected))
                set(2, Cell(2, 0, XSelected))
                set(7, Cell(1, 2, OSelected))
            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(1, 2, OSelected)))
        }

    @Test
    fun checkForWinner_returnOPlayerIfThirdColumnHasOSelected() =
        runBlockingTest {
            val board = Board(cells = cleanBoardCells.toMutableList().apply {
                set(0, Cell(0, 0, XSelected))
                set(6, Cell(0, 2, OSelected))
                set(3, Cell(0, 1, XSelected))
                set(7, Cell(1, 2, OSelected))
                set(5, Cell(2, 1, XSelected))
                set(8, Cell(2, 2, OSelected))
            })
            assertEquals(OPlayer, winnerCheckHelper.checkForWinner(board, Cell(2, 2, OSelected)))
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