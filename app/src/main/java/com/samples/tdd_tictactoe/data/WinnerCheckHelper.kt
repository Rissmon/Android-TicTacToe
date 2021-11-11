package com.samples.tdd_tictactoe.data

import com.samples.tdd_tictactoe.model.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.lang.Math.sqrt

class WinnerCheckHelper constructor(private val dispatcher: CoroutineDispatcher) {

    /**
     * Checks in a worker thread if the last selected cell is the 3rd in a
     * line for the same [PlayerType].
     * It returns the [PlayerType] that wins or null if none of the player wins.
     */
    suspend fun checkForWinner(board: Board, cell: Cell): PlayerType? =
        withContext(dispatcher) {
            board.cells.filterNot { it.state == Clear }.let { selectedCells ->
                selectedCells.forEach {
                    if (it.row == cell.row && it.column == cell.column) {
                        if (board.checkLineInNeighboursForCell(it)) {
                            return@withContext it.getPlayer()
                        }
                    }
                }
            }
            null
        }

    private fun Board.checkLineInNeighboursForCell(cell: Cell) = checkLineDiagonals(cell)

    private fun Board.checkLineDiagonals(cell: Cell): Boolean {
        val boardSideSize = sqrt(cells.size.toDouble()).toInt()
        val selectedCellInDiagonalRight = mutableListOf<Cell>()
        for (i in 0 until boardSideSize) {
            val cellInDiagonal = cells.find {
                it.row == i &&
                        it.column == i &&
                        it.state == cell.state
            }
            if (cellInDiagonal != null) {
                selectedCellInDiagonalRight.add(cell)
            }
        }
        if (selectedCellInDiagonalRight.size >= 3) {
            return true
        }

        return false
    }

    private fun Cell.getPlayer() =
        when (state) {
            OSelected -> OPlayer
            XSelected -> XPlayer
            else -> null
        }
}
