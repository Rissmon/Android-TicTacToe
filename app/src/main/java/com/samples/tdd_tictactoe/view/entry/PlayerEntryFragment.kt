package com.samples.tdd_tictactoe.view.entry

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.base.BaseFragment
import com.samples.tdd_tictactoe.databinding.FragmentPlayerEntryBinding

class PlayerEntryFragment :
    BaseFragment<FragmentPlayerEntryBinding>(R.layout.fragment_player_entry) {
    private val viewModel: PlayerEntryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataBinding().playerEntryViewModel = viewModel
    }
}