package com.samples.tdd_tictactoe.view.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.base.BaseFragment
import com.samples.tdd_tictactoe.databinding.FragmentBoardBinding
import com.samples.tdd_tictactoe.di.BoardSize
import com.samples.tdd_tictactoe.model.Board
import com.samples.tdd_tictactoe.view.game.adapter.GameAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameBoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {
    private val gameBoardFragmentArgs: GameBoardFragmentArgs by navArgs()

    @Inject
    lateinit var assistedFactory: GameViewModelAssistedFactory

    @JvmField
    @Inject
    @BoardSize
    var boardSize: Int = 3
    var gameAdapter: GameAdapter? = null

    private val gameViewModel: GameViewModel by viewModels {
        provideFactory(assistedFactory, gameBoardFragmentArgs.playerData)
    }

    private val onBoardStateChange = Observer<Board> { board ->
        gameAdapter?.submitList(board.cells.map { it.copy() })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(getDataBinding()) {
            viewModel = gameViewModel
            gameAdapter = GameAdapter { cell ->
                gameViewModel.onCellClicked(cell)
            }
            gameRecyclerView.adapter = gameAdapter
            gameRecyclerView.layoutManager = GridLayoutManager(context, boardSize)
            gameRecyclerView.itemAnimator = null
        }
        initViewStateObservers()
    }

    private fun initViewStateObservers() {
        gameViewModel.boardState.observe(viewLifecycleOwner, onBoardStateChange)
    }

}
