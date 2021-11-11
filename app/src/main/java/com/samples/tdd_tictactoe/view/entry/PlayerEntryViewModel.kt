package com.samples.tdd_tictactoe.view.entry

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samples.tdd_tictactoe.common.extension.empty

class PlayerEntryViewModel : ViewModel() {
    //Value need to mutable since there is databinding
    val playerX: MutableLiveData<String> = MutableLiveData(String.empty())
    val playerO: MutableLiveData<String> = MutableLiveData(String.empty())
}


