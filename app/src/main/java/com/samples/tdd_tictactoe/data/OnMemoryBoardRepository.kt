package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.di.BoardSize
import com.samples.tdd_tictactoe.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class OnMemoryBoardRepository @Inject constructor(
    private val winnerCheckHelper: WinnerCheckHelper,
    @BoardSize private val boardSize: Int
) : BoardRepository {

    private var boardFlow: MutableStateFlow<Board> = MutableStateFlow(getClearBoard())
    private var nextPlayerType: PlayerType = XPlayer

    /**
     * Game board containing cells boardSize
     */
    override suspend fun getBoard(): StateFlow<Board> = boardFlow

    /**
     * Make cell selection in board.
     * @param cell: The cell which the user( {@param player)  selected.
     */
    override suspend fun updateCellSelection(cell: Cell, playerType: PlayerType) =
        if (!cell.isClearInBoard()) {
            Result.failure(IllegalArgumentException("Cell was already selected"))
        } else {
            applySelectedStateForPlayer(cell, playerType)
        }

    /**
     * Clear the board to initial state(Game starting state)
     */
    override suspend fun clearCellSelection() {
        boardFlow.value = getClearBoard()
    }

    override suspend fun getNextPlayer(): PlayerType {
        return nextPlayerType
    }

    /**
     * Get the game status based on the game board cells.
     * @param cell: The last selected cell. This will help to verify with adjacent cells to find the game status
     * @see GameState
     */
    override suspend fun getGameStatus(cell: Cell): GameState {
        return boardFlow.value.let { board ->
            val gameState = winnerCheckHelper.checkForWinner(board, cell)?.let { winner ->
                GameState.Winner
            } ?: if (board.isCompleted()) {
                GameState.Draw
            } else {
                GameState.Ongoing
            }
            gameState
        }
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
        nextPlayerType=XPlayer
        return Board(cellList)
    }

    /**
     * Check to identify the cell is already in selected sate
     */
    private fun Cell.isClearInBoard(): Boolean =
        boardFlow.value.cells.contains(this.copy(state = Clear))

    /**
     * Removes the cell that changes state if it was not previously selected. Then added it
     * at the list with the new state.
     */
    private fun applySelectedStateForPlayer(
        cell: Cell,
        currentPlayerType: PlayerType
    ): Result<Unit> {
        with(boardFlow.value.cells.toMutableList()) {
            val newCell = cell.copy(
                state = when (currentPlayerType) {
                    OPlayer -> OSelected
                    XPlayer -> XSelected
                }
            )
            val index = indexOf(cell)
            remove(cell)
            add(index, newCell)
            nextPlayerType = when (currentPlayerType) {
                is XPlayer -> OPlayer
                is OPlayer -> XPlayer
            }
            boardFlow.value = Board(this)
            return Result.success(Unit)
        }
    }

}
