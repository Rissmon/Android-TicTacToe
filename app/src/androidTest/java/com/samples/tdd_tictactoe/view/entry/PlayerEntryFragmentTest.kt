package com.samples.tdd_tictactoe.view.entry

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.samples.tdd_tictactoe.R
import com.samples.tdd_tictactoe.utils.*
import org.junit.Assert.assertEquals
import org.junit.Test


class PlayerEntryFragmentTest {

    @Test
    fun verifyViews_displayedWithCorrectInformation() {
        launchFragment()
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
        launchFragment()
        onView(withId(R.id.buttonPlay)).perform(ViewActions.click())
        validateSnackbarMessage(R.string.enter_all_fields)
    }

    @Test
    fun shouldShowErrorMessage_WhenEitherOfTheInputFieldsAreEmpty() {
        //Validate Player 1
        launchFragment()
        onView(withId(R.id.editTextPlayer1)).perform(ViewActions.typeText(PLAYER1))
        onView(withId(R.id.buttonPlay)).perform(ViewActions.click())
        validateSnackbarMessage(R.string.enter_all_fields)

        //Validate Player 2
        onView(withId(R.id.editTextPlayer1)).perform(ViewActions.clearText())
        onView(withId(R.id.editTextPlayer2)).perform(ViewActions.typeText(PLAYER2))
        onView(withId(R.id.buttonPlay)).perform(ViewActions.click())
        validateSnackbarMessage(R.string.enter_all_fields)
    }

    @Test
    fun shouldGameScreen_WhenAllInputFieldsAreValid() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val scenario = launchFragment()
        scenario.onFragment {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.entryFragment)
            Navigation.setViewNavController(it.requireView(), navController)
        }
        onView(withId(R.id.editTextPlayer1)).perform(ViewActions.typeText(PLAYER1))
        onView(withId(R.id.editTextPlayer2)).perform(ViewActions.typeText(PLAYER2))
        onView(withId(R.id.buttonPlay)).perform(ViewActions.click())
        assertEquals(navController.currentDestination?.id, R.id.gameFragment)
    }

    private fun launchFragment() =
        launchFragmentInContainer<PlayerEntryFragment>(themeResId = R.style.ThemeTicTacToe)

}