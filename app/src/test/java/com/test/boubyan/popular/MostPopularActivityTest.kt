package com.test.boubyan.popular


import com.test.boubyan.R
import com.test.boubyan.ui.popular.MostPopularActivity
import org.junit.Rule
import org.junit.Test
import com.taingmeng.espresso.recyclerviewassertions.CustomAssertions.Companion.hasItemCount
import com.taingmeng.espresso.recyclerviewassertions.CustomMatchers.Companion.withItemCount
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule

class MostPopularActivityTest  {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MostPopularActivity>(MostPopularActivity::class.java)

    @Test
    fun countPrograms() {
        onView(withId(R.id.recyclerView))
                .check(matches(withItemCount(12)))
    }

    @Test
    fun countProgramsWithViewAssertion() {
        onView(withId(R.id.recyclerView))
                .check(hasItemCount(12))
    }
}
