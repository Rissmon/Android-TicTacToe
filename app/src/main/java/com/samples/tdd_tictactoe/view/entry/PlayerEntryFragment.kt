package com.samples.tdd_tictactoe.view.entry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.base.BaseFragment
import com.samples.tdd_tictactoe.common.extension.showSnackbar
import com.samples.tdd_tictactoe.common.nonNull
import com.samples.tdd_tictactoe.databinding.FragmentPlayerEntryBinding
import com.samples.tdd_tictactoe.model.PlayerData

class PlayerEntryFragment :
    BaseFragment<FragmentPlayerEntryBinding>(R.layout.fragment_player_entry) {
    private val viewModel: PlayerEntryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataBinding().playerEntryViewModel = viewModel
        getDataBinding().buttonPlay.setOnClickListener {
            if (viewModel.isPlayerFieldNullOrEmpty)
                getDataBinding().root.showSnackbar(R.string.enter_all_fields)
            else navigate()

        }
    }

    private fun navigate() {
        val actions = PlayerEntryFragmentDirections.actionEntryFragmentToGameFragment(
            PlayerData(
                nonNull(viewModel.playerX.value),
                nonNull(viewModel.playerO.value)
            )
        )
        findNavController().navigate(actions)
    }
}