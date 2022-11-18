package com.example.finalprojectwizelinecryptocurrencies.ui.home

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.finalprojectwizelinecryptocurrencies.R

import org.junit.Test

class HomeCryptocurrencyFragmentTest {

    @Test
    fun navHome_to_navDetail() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val titleScenario = launchFragmentInContainer<HomeCryptocurrencyFragment>()
        titleScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)

            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.rvCryptocurrency)).perform(ViewActions.click())
        assert(navController.currentDestination?.id == R.id.detailCryptocurrencyFragment)
    }
}