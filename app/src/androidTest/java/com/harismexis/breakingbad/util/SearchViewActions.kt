package com.harismexis.breakingbad.util

import android.view.View
import android.widget.SearchView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

class SearchViewActionExtension {

    companion object {

        fun submitQuery(text: String): ViewAction {

            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
                }

                override fun getDescription(): String {
                    return "Set text to searchView and submit search query"
                }

                override fun perform(uiController: UiController, view: View) {
                    (view as SearchView).setQuery(text, true)
                }
            }
        }

    }
}