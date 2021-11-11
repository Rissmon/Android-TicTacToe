package com.samples.tdd_tictactoe.utils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.R

internal fun ViewInteraction.isDisplayed() = check(matches(ViewMatchers.isDisplayed()))

internal fun ViewInteraction.isClickable() = check(matches(ViewMatchers.isClickable()))

internal fun ViewInteraction.withText(stringRes: Int) =
    check(matches(ViewMatchers.withText(stringRes)))

fun validateSnackbarMessage(id: Int) {
    onView(ViewMatchers.withId(R.id.snackbar_text)).withText(id)
}