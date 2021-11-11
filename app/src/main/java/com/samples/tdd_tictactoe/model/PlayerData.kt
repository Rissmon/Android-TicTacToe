package com.samples.tdd_tictactoe.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerData(val playerX: String, val playerO: String) : Parcelable

