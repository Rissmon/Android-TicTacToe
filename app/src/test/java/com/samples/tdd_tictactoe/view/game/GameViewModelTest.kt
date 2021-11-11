package com.samples.tdd_tictactoe.view.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samples.tdd_tictactoe.common.MainCoroutineRule
import com.samples.tdd_tictactoe.common.getOrAwaitValue
import com.samples.tdd_tictactoe.data.BoardRepository
import com.samples.tdd_tictactoe.model.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private val testDispatcher = TestCoroutineDispatcher()
    private val playerData = PlayerData("Player1", "Player2")
    private val boardRepository = mockk<BoardRepository>(relaxed = true)
    private lateinit var gameViewModel: GameViewModel

    private fun createGameViewModel() {
        gameViewModel = GameViewModel(boardRepository, testDispatcher, playerData)
    }

    @Test
    fun viewModel_verifyInitialState() = runBlockingTest {
        val board = Board(emptyList())
        coEvery { boardRepository.getBoard() } returns flowOf(board)
        createGameViewModel()
        coVerify(exactly = 1) {
            boardRepository.getBoard()
        }
        assertEquals(gameViewModel.boardState.getOrAwaitValue(), board)
    }

    @Test
    fun onCellClicked_checkGameStateReturnsWinner() =
        runBlockingTest {
            val expectedPlayer = XPlayer
            coEvery { boardRepository.getNextPlayer() } returns expectedPlayer
            val givenCell = Cell(0, 0)
            coEvery {
                boardRepository.updateCellSelection(givenCell, expectedPlayer)
            } returns Result.success(Unit)
            coEvery { boardRepository.getGameStatus(givenCell) } returns GameState.Winner
            createGameViewModel()
            gameViewModel.onCellClicked(givenCell)
            coVerify(exactly = 1) {
                boardRepository.updateCellSelection(givenCell, expectedPlayer)
            }
            assertEquals(gameViewModel.isGameFinished.getOrAwaitValue(), true)
            assertEquals(gameViewModel.gameResult.getOrAwaitValue(), playerData.playerX)
        }

}