package com.samples.tdd_tictactoe.view

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.samples.tdd_tictactoe.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun init() {
        activityScenario = launchActivity()
        activityScenario.onActivity { navController.setGraph(R.navigation.nav_graph) }
    }

    @Test
    fun activityNavigation_ToInitialScreen() {
        assertEquals(navController.currentDestination?.id, R.id.entryFragment)
    }

}
