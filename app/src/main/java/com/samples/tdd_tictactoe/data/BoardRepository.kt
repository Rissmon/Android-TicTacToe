package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.model.Board
import com.samples.tdd_tictactoe.model.Cell
import com.samples.tdd_tictactoe.model.PlayerType
import kotlinx.coroutines.flow.Flow

interface BoardRepository {

    suspend fun getBoard(): Flow<Board>
    suspend fun updateCellSelection(cell: Cell, playerType: PlayerType): Result<Unit>
    suspend fun clearCellSelection()
    suspend fun getNextPlayer(): PlayerType
}
