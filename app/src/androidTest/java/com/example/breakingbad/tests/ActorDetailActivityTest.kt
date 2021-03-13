package com.example.breakingbad.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.breakingbad.R
import com.example.breakingbad.setup.base.InstrumentedTestSetup
import com.example.breakingbad.domain.Actor
import com.example.breakingbad.framework.extensions.getLinkSpanned
import com.example.breakingbad.setup.mockvm.MockActorDetailViewModel
import com.example.breakingbad.presentation.actordetail.viewmodel.ActorDetailViewModel
import com.example.breakingbad.presentation.actordetail.ui.ActorDetailActivity
import io.mockk.every
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActorDetailActivityTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<ActorDetailActivity> =
        ActivityTestRule(
            ActorDetailActivity::class.java,
            false, false
        )

    private lateinit var mockViewModel: ActorDetailViewModel
    private lateinit var mockItem: Actor

    @Before
    fun setup() {
        mockViewModel = MockActorDetailViewModel.getMockActorDetailViewModel()
        every { mockViewModel.retrieveItemById(any()) } returns Unit
        mockItem = mockParser.getMockBBCharValid()
    }

    @Test
    fun liveDataChanges_then_uiUpdatedWithExpectedData() {
        // given
        every { mockViewModel.model } returns MockActorDetailViewModel.model
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.img)).check(matches(isDisplayed()))

        onView(withId(R.id.txt_name_label)).check(matches(withText(getString(R.string.label_name))))
        onView(withId(R.id.txt_name)).check(matches(withText(mockItem.name)))

        onView(withId(R.id.txt_birthday_label)).check(matches(withText(getString(R.string.label_birthday))))
        onView(withId(R.id.txt_birthday)).check(matches(withText(mockItem.birthday)))

        onView(withId(R.id.txt_status_label)).check(matches(withText(getString(R.string.label_status))))
        onView(withId(R.id.txt_status)).check(matches(withText(mockItem.status)))

        onView(withId(R.id.txt_nickname_label)).check(matches(withText(getString(R.string.label_nickname))))
        onView(withId(R.id.txt_nickname)).check(matches(withText(mockItem.nickname)))

        onView(withId(R.id.txt_portrayed_label)).check(matches(withText(getString(R.string.label_portrayed))))
        onView(withId(R.id.txt_portrayed)).check(matches(withText(mockItem.portrayed)))

        onView(withId(R.id.txt_category_label)).check(matches(withText(getString(R.string.label_category))))
        onView(withId(R.id.txt_category)).check(matches(withText(mockItem.category.toString())))

        onView(withId(R.id.txt_img_url)).check(
            matches(
                withText(
                    getLinkSpanned(
                        getString(R.string.missing_img_url),
                        getString(R.string.character_image),
                        mockItem.img
                    )
                        .toString()
                )
            )
        )
    }

    private fun launchActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockActorDetailViewModel.mModel.value = mockItem
        }
    }

    private fun getString(id: Int): String {
        return InstrumentationRegistry.getInstrumentation()
            .targetContext.resources.getString(id)
    }

}
