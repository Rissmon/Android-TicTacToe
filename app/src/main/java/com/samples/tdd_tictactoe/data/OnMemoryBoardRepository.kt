package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class OnMemoryBoardRepository constructor(
    private val boardSize: Int
) : BoardRepository {

    private var boardFlow: MutableStateFlow<Board> = MutableStateFlow(getClearBoard())
    private var nextPlayerType: PlayerType = XPlayer

    /**
     * Game board containing cells boardSize
     */
    override suspend fun getBoard(): StateFlow<Board> = boardFlow

    override suspend fun updateCellSelection(cell: Cell, playerType: PlayerType): Result<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun clearCellSelection() {
        TODO("Not yet implemented")
    }

    override suspend fun getNextPlayer(): PlayerType {
        TODO("Not yet implemented")
    }

    /**
     * Get initial Cells with clear status
     */
    private fun getClearBoard(): Board {
        val cellList = mutableListOf<Cell>()
        val totalCell = (boardSize * boardSize) - 1
        for (index in 0..totalCell) {
            cellList.add(
                Cell(
                    row = (index / boardSize),
                    column = index % boardSize,
                    state = Clear
                )
            )
        }
        return Board(cellList)
    }

}
