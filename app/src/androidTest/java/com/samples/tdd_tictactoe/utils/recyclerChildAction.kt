package com.samples.tdd_tictactoe.utils

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.hamcrest.CoreMatchers.any
import org.hamcrest.Matcher


fun <T : View> recyclerChildAction(@IdRes id: Int, block: T.() -> Unit): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return any(View::class.java)
        }

        override fun getDescription(): String {
            return "Performing action on RecyclerView child item"
        }

        override fun perform(
            uiController: UiController,
            view: View
        ) {
            view.findViewById<T>(id).block()
        }
    }

}
