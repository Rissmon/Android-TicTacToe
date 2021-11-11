package com.samples.tdd_tictactoe.common

import com.samples.tdd_tictactoe.common.extension.empty

fun nonNull(value: String?) = value ?: String.empty()