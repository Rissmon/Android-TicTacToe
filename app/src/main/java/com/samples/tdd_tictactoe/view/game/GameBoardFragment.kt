package com.samples.tdd_tictactoe.view.game

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.base.BaseFragment
import com.samples.tdd_tictactoe.databinding.FragmentBoardBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameBoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {
    private val gameBoardFragmentArgs: GameBoardFragmentArgs by navArgs()

    @Inject
    lateinit var assistedFactory: GameViewModelAssistedFactory

    private val gameViewModel: GameViewModel by viewModels {
        provideFactory(assistedFactory, gameBoardFragmentArgs.playerData)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(getDataBinding()) {
            viewModel = gameViewModel
        }
    }

}
