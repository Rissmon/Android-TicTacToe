package com.samples.tdd_tictactoe.view.game

import android.content.Context
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import androidx.test.platform.app.InstrumentationRegistry
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.model.PlayerData
import com.samples.tdd_tictactoe.utils.PLAYER1
import com.samples.tdd_tictactoe.utils.PLAYER2
import com.samples.tdd_tictactoe.utils.launchFragmentInHiltContainer
import com.samples.tdd_tictactoe.utils.withText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class GameBoardFragmentTest {
    var appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private val player = PlayerData(PLAYER1, PLAYER2)
    private val fragmentArgs = bundleOf(
        "playerData" to player,
    )
    private val boardSize = 3
    private val totalCell = boardSize * boardSize

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun verifyViews_AreDisplayed() {
        launchFragment()
        onView(withId(R.id.textViewHeader)).apply {
            ViewMatchers.isDisplayed()
            withText(R.string.board_screen_title)
        }
        onView(withId(R.id.textViewNextPlayer)).apply {
            ViewMatchers.isDisplayed()
            check(
                matches(
                    withSubstring(
                        appContext.getString(R.string.next_player_title_label, player.playerX)
                    )
                )
            )
        }
        onView(withId(R.id.buttonRestartGame)).apply {
            ViewMatchers.isDisplayed()
            ViewMatchers.isClickable()
            withText(R.string.restart_game_button)
        }
        onView(withId(R.id.gameRecyclerView)).apply {
            ViewMatchers.isDisplayed()
        }
    }

    @Test
    fun recyclerView_displayWithExpectedItemCount() {
        launchFragment()
        onView(withId(R.id.gameRecyclerView)).apply {
            ViewMatchers.isDisplayed()
            check(matches(ViewMatchers.hasChildCount(totalCell)))
        }
    }

    private fun launchFragment() = launchFragmentInHiltContainer<GameBoardFragment>(
        fragmentArgs,
        themeResId = R.style.ThemeTicTacToe
    )
}