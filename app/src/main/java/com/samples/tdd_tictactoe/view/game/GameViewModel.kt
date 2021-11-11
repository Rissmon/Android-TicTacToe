package com.samples.tdd_tictactoe.view.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.tdd_tictactoe.common.extension.empty
import com.samples.tdd_tictactoe.data.BoardRepository
import com.samples.tdd_tictactoe.di.BackgroundDispatcher
import com.samples.tdd_tictactoe.model.*
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GameViewModel @AssistedInject constructor(
    private val boardRepository: BoardRepository,
    @BackgroundDispatcher private val dispatcher: CoroutineDispatcher,
    @Assisted private val playerData: PlayerData,
) : ViewModel() {

    val boardState: MutableLiveData<Board> = MutableLiveData()
    private val _nextPlayer = MutableLiveData(playerData.playerX)
    val nextPlayer: LiveData<String> = _nextPlayer
    private val _isGameDraw = MutableLiveData(false)
    val isGameDraw: LiveData<Boolean> = _isGameDraw
    private val _isGameFinished = MutableLiveData(false)
    val isGameFinished: LiveData<Boolean> = _isGameFinished
    private val _gameResult = MutableLiveData(String.empty())
    val gameResult: LiveData<String> = _gameResult

    init {
        viewModelScope.launch {
            observeBoardUpdates()
        }
    }

    private suspend fun observeBoardUpdates() {
        boardRepository.getBoard()
            .flowOn(dispatcher)
            .collect {
                boardState.postValue(it)
            }
    }

    fun onRestartButtonClicked() {
        viewModelScope.launch {
            boardRepository.clearCellSelection()
            updateTurn(XPlayer)
        }
    }

    fun onCellClicked(cell: Cell) {
        viewModelScope.launch {
            val currentPlayerType: PlayerType = boardRepository.getNextPlayer()
            boardRepository.updateCellSelection(cell, currentPlayerType).fold(
                onSuccess = { checkGameState(currentPlayerType, cell) },
                onFailure = { Log.i("updateCellSelection", "Error") }
            )
        }
    }

    private suspend fun checkGameState(currentPlayerType: PlayerType, cell: Cell) {
        when (boardRepository.getGameStatus(cell)) {
            GameState.Draw -> {
                _isGameFinished.value = true
                _isGameDraw.value = true
            }
            GameState.Ongoing -> updateTurn(boardRepository.getNextPlayer())
            is GameState.Winner -> {
                _isGameFinished.value = true
                _gameResult.value = when (currentPlayerType) {
                    XPlayer -> playerData.playerX
                    OPlayer -> playerData.playerO
                }
            }
        }
    }

    fun updateTurn(playerType: PlayerType) {
        _nextPlayer.value = when (playerType) {
            XPlayer -> playerData.playerX
            OPlayer -> playerData.playerO
        }
        _isGameFinished.value = false
        _gameResult.value = String.empty()
        _isGameDraw.value = false
    }

}


