package com.samples.tdd_tictactoe.view.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samples.tdd_tictactoe.common.MainCoroutineRule
import com.samples.tdd_tictactoe.common.getOrAwaitValue
import com.samples.tdd_tictactoe.data.BoardRepository
import com.samples.tdd_tictactoe.model.Board
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

    private val boardRepository = mockk<BoardRepository>(relaxed = true)
    private lateinit var gameViewModel: GameViewModel

    private fun createGameViewModel() {
        gameViewModel = GameViewModel(boardRepository, testDispatcher)
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
}