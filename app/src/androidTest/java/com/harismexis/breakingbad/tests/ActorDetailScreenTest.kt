package com.harismexis.breakingbad.tests

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.domain.Actor.Companion.occupationString
import com.harismexis.breakingbad.presentation.result.ActorDetailResult
import com.harismexis.breakingbad.presentation.result.ActorsResult
import com.harismexis.breakingbad.presentation.screens.home.ui.activity.MainActivity
import com.harismexis.breakingbad.setup.base.InstrumentedTestSetup
import com.harismexis.breakingbad.setup.testutil.getExpectedText
import com.harismexis.breakingbad.setup.viewmodel.MockActorDetailVmProvider
import com.harismexis.breakingbad.setup.viewmodel.MockHomeVmProvider
import io.mockk.every
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActorDetailScreenTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    private val mockHomeViewModel = MockHomeVmProvider.mockHomeViewModel
    private var mockActors = actorsParser.getMockActorsWhenJsonHasAllItemsValid()
    private var actorsSuccess = ActorsResult.ActorsSuccess(mockActors)
    private var clickIndexOnSearchList = 0

    private val mockDetailViewModel = MockActorDetailVmProvider.mockActorDetailViewModel
    private var mockActor = mockActors[0]
    private var mockActorId = mockActor.char_id
    private lateinit var actorDetailSuccess: ActorDetailResult.ActorSuccess

    init {
        every { mockHomeViewModel.actorsResult } returns MockHomeVmProvider.fakeActorsResult
        every { mockDetailViewModel.retrieveActorById(mockActorId) } answers {
            MockActorDetailVmProvider.fakeActorDetailResult.value = actorDetailSuccess
        }
    }

    @Test
    fun clickFirstSearchResult_opensActorDetailsAndShowsExpectedActorData() {
        // given
        mockActors = actorsParser.getMockActorsWhenJsonHasAllItemsValid()
        mockActorDetailResultSuccess()

        // when
        openHomeAndClickFirstItemToOpenActorDetails()

        // then
        verifyActorDetailsAreTheExpected()
    }

    private fun verifyActorDetailsAreTheExpected() {
        verifyLabel(R.id.txt_name_label, R.string.label_name)
        verifyValue(R.id.txt_name, mockActor.name)

        verifyLabel(R.id.txt_nickname_label, R.string.label_nickname)
        verifyValue(R.id.txt_nickname, mockActor.nickname)

        verifyLabel(R.id.txt_portrayed_label, R.string.label_portrayed)
        verifyValue(R.id.txt_portrayed, mockActor.portrayed)

        verifyLabel(R.id.txt_occupation_label, R.string.label_occupation)
        verifyValue(R.id.txt_occupation, mockActor.occupationString())

        verifyLabel(R.id.txt_birthday_label, R.string.label_birthday)
        verifyValue(R.id.txt_birthday, mockActor.birthday)

        verifyLabel(R.id.txt_status_label, R.string.label_status)
        verifyValue(R.id.txt_status, mockActor.status)

        verifyLabel(R.id.txt_category_label, R.string.label_category)
        verifyValue(R.id.txt_category, mockActor.category)
    }

    private fun verifyLabel(@IdRes textViewId: Int, @StringRes expectedStringResId: Int) {
        onView(withId(textViewId)).check(matches(withText(expectedStringResId)))
    }

    private fun verifyValue(@IdRes textViewId: Int, value: String?) {
        onView(withId(textViewId)).check(matches(withText(getExpectedText(value))))
    }

    private fun mockActorDetailResultSuccess() {
        actorDetailSuccess = ActorDetailResult.ActorSuccess(mockActor)
        every { mockDetailViewModel.actorDetailResult } returns MockActorDetailVmProvider.fakeActorDetailResult
    }

    private fun openHomeAndClickFirstItemToOpenActorDetails() {
        launchActivity()
        clickRecyclerAt(clickIndexOnSearchList) // click an actor on search list to open ActorDetail
    }

    private fun launchActivity() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockHomeVmProvider.fakeActorsResult.value = actorsSuccess
        }
    }

    private fun clickRecyclerAt(position: Int) {
        onView(withId(R.id.home_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

}