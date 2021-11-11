package com.samples.tdd_tictactoe.view.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samples.tdd_tictactoe.data.BoardRepository
import com.samples.tdd_tictactoe.di.BackgroundDispatcher
import com.samples.tdd_tictactoe.model.Board
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GameViewModel constructor(
    private val boardRepository: BoardRepository,
    @BackgroundDispatcher private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

    val boardState: MutableLiveData<Board> = MutableLiveData()

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

}


