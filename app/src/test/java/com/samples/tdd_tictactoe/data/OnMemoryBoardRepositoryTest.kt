package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.common.MainCoroutineRule
import com.samples.tdd_tictactoe.model.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OnMemoryBoardRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private val winnerCheckHelper: WinnerCheckHelper = mockk()


    private lateinit var repository: OnMemoryBoardRepository
    private val boardSize: Int = 3
    private val cellSize: Int = boardSize * boardSize

    @Before
    fun before() {
        repository = OnMemoryBoardRepository(winnerCheckHelper, boardSize)
    }

    @Test
    fun board_returnClearBoard() = runBlockingTest {
        val board = repository.getBoard().first()
        assertEquals(cellSize, board.cells.filter { it.state == Clear }.size)
        assertNull(board.cells.find { it.state == XSelected })
        assertNull(board.cells.find { it.state == OSelected })
    }

    @Test
    fun cellSelection_boardStateChangedIfACellIsSelected() = runBlockingTest {
        val initialBoard = repository.getBoard().first()
        repository.updateCellSelection(Cell(0, 0), XPlayer)
        repository.updateCellSelection(Cell(0, 1), OPlayer)
        val boardAfterUpdate = repository.getBoard().first()
        with(initialBoard) {
            assertEquals(9, cells.filter { it.state == Clear }.size)
            assertEquals(0, cells.filter { it.state == XSelected }.size)
            assertEquals(0, cells.filter { it.state == OSelected }.size)
        }
        with(boardAfterUpdate) {
            assertEquals(7, cells.filter { it.state == Clear }.size)
            assertEquals(1, cells.filter { it.state == XSelected }.size)
            assertEquals(1, cells.filter { it.state == OSelected }.size)
        }
    }

    @Test
    fun clearCellSelection_willClearTheBoardToInitialState() =
        runBlockingTest {
            repository.clearCellSelection()
            val board = repository.getBoard().first()
            assertEquals(cellSize, board.cells.filter { it.state == Clear }.size)
            assertNull(board.cells.find { it.state == XSelected })
            assertNull(board.cells.find { it.state == OSelected })
        }

    @Test
    fun getNextPlayer_returningXPlayer() =
        runBlockingTest {
            repository.updateCellSelection(Cell(0, 0), XPlayer)
            repository.updateCellSelection(Cell(1, 0), OPlayer)
            repository.updateCellSelection(Cell(2, 0), XPlayer)
            repository.updateCellSelection(Cell(0, 1), OPlayer)
            Assert.assertTrue(repository.getNextPlayer() is XPlayer)
        }

    @Test
    fun getNextPlayer_returnOPlayer() =
        runBlockingTest {
            repository.updateCellSelection(Cell(0, 0), XPlayer)
            repository.updateCellSelection(Cell(1, 0), OPlayer)
            repository.updateCellSelection(Cell(2, 0), XPlayer)
            Assert.assertTrue(repository.getNextPlayer() is OPlayer)
        }

    @Test
    fun getGameStatus_returnWinGameStatus() =
        runBlockingTest {
            val cell = Cell(0, 2, XSelected)
            coEvery {
                winnerCheckHelper.checkForWinner(
                    repository.getBoard().value,
                    cell
                )
            } returns XPlayer
            val winner = repository.getGameStatus(cell)
            Assert.assertTrue(winner is GameState.Winner)
        }

    @Test
    fun getGameStatus_returnOngoingGameStatus() =
        runBlockingTest {
            val cell = Cell(0, 2, XSelected)
            coEvery {
                winnerCheckHelper.checkForWinner(
                    repository.getBoard().value,
                    cell
                )
            } returns null
            val winner = repository.getGameStatus(cell)
            Assert.assertTrue(winner is GameState.Ongoing)
        }

}