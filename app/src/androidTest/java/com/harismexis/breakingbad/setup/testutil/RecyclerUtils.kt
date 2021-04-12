package com.harismexis.breakingbad.setup.testutil

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matchers

fun withRecycler(recyclerViewId: Int): RecyclerMatcher {
    return RecyclerMatcher(recyclerViewId)
}

fun verifyRecyclerItemAt(
    @IdRes recyclerId: Int,
    index: Int,
    @IdRes viewId: Int,
    expectedText: String?
) {
    Espresso.onView(withRecycler(recyclerId).atPositionOnView(index, viewId))
        .check(ViewAssertions.matches(ViewMatchers.withText(expectedText)))
}

fun verifyRecyclerItemGoneAt(
    @IdRes recyclerId: Int,
    index: Int,
    @IdRes viewId: Int
) {
    Espresso.onView(withRecycler(recyclerId).atPositionOnView(index, viewId))
        .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
}
