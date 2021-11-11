package com.samples.tdd_tictactoe.view.game

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.samples.tdd_tictactoe.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GameBoardFragment : Fragment(R.layout.fragment_board) {
    private val gameBoardFragmentArgs: GameBoardFragmentArgs by navArgs()

    @Inject
    lateinit var assistedFactory: GameViewModelAssistedFactory

    private val gameViewModel: GameViewModel by viewModels {
        provideFactory(assistedFactory, gameBoardFragmentArgs.playerData)
    }


}
