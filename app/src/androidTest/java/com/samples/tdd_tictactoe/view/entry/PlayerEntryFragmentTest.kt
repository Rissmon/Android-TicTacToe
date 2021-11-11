package com.samples.tdd_tictactoe.view.entry

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.utils.isClickable
import com.samples.tdd_tictactoe.utils.isDisplayed
import com.samples.tdd_tictactoe.utils.validateSnackbarMessage
import com.samples.tdd_tictactoe.utils.withText
import org.junit.Test


class PlayerEntryFragmentTest {

    @Test
    fun verifyViews_displayedWithCorrectInformation() {
        launchFragmentInContainer<PlayerEntryFragment>(themeResId = R.style.ThemeTicTacToe)
        onView(withId(R.id.textViewLetsBegin)).isDisplayed()
        onView(withId(R.id.textViewLetsBegin)).withText(R.string.entry_title_label)
        onView(withId(R.id.textViewDescriptionLabel)).isDisplayed()
        onView(withId(R.id.textViewDescriptionLabel)).withText(R.string.enter_your_name)
        onView(withId(R.id.buttonPlay)).isDisplayed()
        onView(withId(R.id.buttonPlay)).isClickable()
        onView(withId(R.id.buttonPlay)).withText(R.string.entry_title_label)
        onView(withId(R.id.inputPlayer1)).isDisplayed()
        onView(withId(R.id.inputPlayer2)).isDisplayed()
    }

    @Test
    fun shouldShowErrorMessage_WhenAllInputFieldsAreEmpty() {
        launchFragmentInContainer<PlayerEntryFragment>(themeResId = R.style.ThemeTicTacToe)
        onView(withId(R.id.buttonPlay)).perform(ViewActions.click())
        validateSnackbarMessage(R.string.enter_all_fields)
    }

}