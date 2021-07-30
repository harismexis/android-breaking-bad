package com.harismexis.breakingbad.util

import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import java.util.*

class RecyclerMatcher(private val recyclerViewId: Int) {

    fun atPositionOnView(
        position: Int,
        targetViewId: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null
            var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = recyclerViewId.toString()
                if (resources != null) {
                    idDescription = try {
                        resources!!.getResourceName(recyclerViewId)
                    } catch (ex: NotFoundException) {
                        String.format("%s (resource name not found)", recyclerViewId)
                    }
                }
                description.appendText("with id: $idDescription")
            }

            override fun matchesSafely(view: View): Boolean {
                resources = view.resources
                if (childView == null) {
                    val recyclerView: RecyclerView = view.rootView.findViewById(
                        recyclerViewId
                    )
                    childView = if (recyclerView != null && recyclerView.id == recyclerViewId) {
                        Objects.requireNonNull(
                            recyclerView.findViewHolderForAdapterPosition(position)
                        )!!.itemView
                    } else {
                        return false
                    }
                }
                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView!!.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
    }

}