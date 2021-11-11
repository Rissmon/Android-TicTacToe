package com.samples.tdd_tictactoe.view.game

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
@HiltAndroidTest
class GameBoardFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

}