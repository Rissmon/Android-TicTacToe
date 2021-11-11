package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.common.MainCoroutineRule
import com.samples.tdd_tictactoe.model.Clear
import com.samples.tdd_tictactoe.model.OSelected
import com.samples.tdd_tictactoe.model.XSelected
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class OnMemoryBoardRepositoryTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private lateinit var repository: OnMemoryBoardRepository
    private val boardSize: Int = 3
    private val cellSize: Int = boardSize * boardSize

    @Before
    fun before() {
        repository = OnMemoryBoardRepository(boardSize)
    }

    @Test
    fun board_returnClearBoard() = runBlockingTest {
        val board = repository.getBoard().first()
        Assert.assertEquals(cellSize, board.cells.filter { it.state == Clear }.size)
        Assert.assertNull(board.cells.find { it.state == XSelected })
        Assert.assertNull(board.cells.find { it.state == OSelected })
    }
}