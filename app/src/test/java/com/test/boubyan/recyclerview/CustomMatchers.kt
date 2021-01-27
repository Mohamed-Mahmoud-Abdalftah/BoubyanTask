package com.taingmeng.espresso.recyclerviewassertions

import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.matcher.ViewMatchers.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomMatchers {
  companion object {
    fun withItemCount(count: Int): Matcher<View> {
      return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

        override fun describeTo(description: Description?) {
          description?.appendText("RecyclerView with item count: $count")
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
          return item?.adapter?.itemCount == count
        }
      }
    }
  }
}
