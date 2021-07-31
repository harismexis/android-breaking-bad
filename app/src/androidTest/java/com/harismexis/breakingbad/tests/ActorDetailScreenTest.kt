package com.harismexis.breakingbad.tests

import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.base.BaseInstrumentedTest
import com.harismexis.breakingbad.mocks.mockActorDetailViewModel
import com.harismexis.breakingbad.mocks.mockHomeViewModel
import com.harismexis.breakingbad.model.domain.Actor.Companion.occupationString
import com.harismexis.breakingbad.model.result.ActorDetailResult
import com.harismexis.breakingbad.model.result.ActorsResult
import com.harismexis.breakingbad.presentation.screens.home.ui.activity.MainActivity
import com.harismexis.breakingbad.util.clickRecyclerAt
import com.harismexis.breakingbad.util.getExpectedText
import io.mockk.every
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActorDetailScreenTest : BaseInstrumentedTest() {

    private var mockActors = mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid()
    private var actorsSuccess = ActorsResult.Success(mockActors)
    private var clickIndexOnSearchResultList = 0

    private var mockActor = mockActors[0]
    private var mockActorId = mockActor.actorId
    private lateinit var actorDetailSuccess: ActorDetailResult.Success

    private val mockActorsResult = MutableLiveData<ActorsResult>()
    private val mockActorDetailResult = MutableLiveData<ActorDetailResult>()

    init {
        every { mockHomeViewModel.actorsResult } returns mockActorsResult
        every { mockActorDetailViewModel.retrieveActorById(mockActorId) } answers {
            mockActorDetailResult.value = actorDetailSuccess
        }
    }

    @Test
    fun clickFirstSearchResult_opensActorDetailsAndShowsExpectedActorData() {
        // given
        mockInitialResultsInHomeScreen()
        mockActorDetailResultSuccess()

        // when
        openHomeAndClickListItemToOpenActorDetails()

        // then
        verifyActorDetailsAreTheExpected()
    }

    private fun mockInitialResultsInHomeScreen() {
        actorsSuccess = ActorsResult.Success(mockActors)
        every { mockHomeViewModel.fetchActors() } answers {
            mockActorsResult.value = actorsSuccess
        }
        every { mockHomeViewModel.actorsResult } returns mockActorsResult
    }

    private fun mockActorDetailResultSuccess() {
        actorDetailSuccess = ActorDetailResult.Success(mockActor)
        every { mockActorDetailViewModel.actorDetailResult } returns mockActorDetailResult
    }

    private fun openHomeAndClickListItemToOpenActorDetails() {
        val scenario = launchActivity<MainActivity>()
        clickRecyclerAt(R.id.home_list, clickIndexOnSearchResultList)
    }

    private fun verifyActorDetailsAreTheExpected() {
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

}