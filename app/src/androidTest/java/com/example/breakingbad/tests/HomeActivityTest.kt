package com.example.breakingbad.tests

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.breakingbad.R
import com.example.breakingbad.setup.base.InstrumentedTestSetup
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.setup.mockvm.MockHomeVmProvider
import com.example.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_ALL_IDS_VALID
import com.example.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_NO_DATA
import com.example.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_TWO_EMPTY
import com.example.breakingbad.parser.BaseMockParser.Companion.EXPECTED_NUM_BBCHARS_WHEN_TWO_IDS_ABSENT
import com.example.breakingbad.presentation.bbcharacterdetail.ui.BBCharacterDetailActivity
import com.example.breakingbad.presentation.home.viewmodel.HomeViewModel
import com.example.breakingbad.setup.testutil.RecyclerViewItemCountAssertion
import com.example.breakingbad.presentation.home.ui.activity.HomeActivity
import com.example.breakingbad.setup.testutil.RecyclerViewMatcher
import io.mockk.every
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<HomeActivity> =
        ActivityTestRule(
            HomeActivity::class.java,
            false, false
        )

    private lateinit var mockViewModel: HomeViewModel
    private lateinit var mockItems: List<BBCharacter>

    @Before
    fun doBeforeTest() {
        Intents.init()
        mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
        mockViewModel = MockHomeVmProvider.provideMockHomeVm()
        every { mockViewModel.bind() } returns Unit
    }

    @Test
    fun remoteFeedHasAllItemsValid_then_homeListHasExpectedItems() {
        // given
        every { mockViewModel.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_BBCHARS_WHEN_ALL_IDS_VALID)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasSomeIdsAbsent_homeListHasExpectedNumberOfItems() {
        // given
        mockItems = mockParser.getMockBBCharsFromFeedWithSomeIdsAbsent()
        every { mockViewModel.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_BBCHARS_WHEN_TWO_IDS_ABSENT)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasAllIdsAbsent_homeListHasNoItems() {
        // given
        mockItems = mockParser.getMockBBCharsFromFeedWithAllIdsAbsent()
        every { mockViewModel.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_BBCHARS_WHEN_NO_DATA)
        )
    }

    @Test
    fun remoteFeedHasSomeJsonItemsEmpty_homeListHasExpectedNumberOfItems() {
        // given
        mockItems = mockParser.getMockBBCharsFromFeedWithSomeItemsEmpty()
        every { mockViewModel.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_BBCHARS_WHEN_TWO_EMPTY)
        )
        verifyRecyclerViewShowsExpectedData()
    }

    @Test
    fun remoteFeedHasEmptyJsonArray_homeListHasNoItems() {
        // given
        mockItems = mockParser.getMockBBCharsFromFeedWithEmptyJsonArray()
        every { mockViewModel.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        onView(withId(R.id.home_list)).check(RecyclerViewItemCountAssertion(mockItems.size))
        onView(withId(R.id.home_list)).check(
            RecyclerViewItemCountAssertion(EXPECTED_NUM_BBCHARS_WHEN_NO_DATA)
        )
    }

    @Test
    fun clickOnHomeListItem_opensDetailActivity() {
        // given
        every { mockViewModel.models } returns MockHomeVmProvider.models
        launchActivityAndMockLiveData()

        // when
        clickRecyclerAt(0)

        // then
        intended(hasComponent(BBCharacterDetailActivity::class.java.name))
    }

    private fun clickRecyclerAt(position: Int) {
        onView(withId(R.id.home_list)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )
    }

    private fun verifyRecyclerViewShowsExpectedData() {
        mockItems.forEachIndexed { index, uiModel ->
            // scroll to item to make sure it's visible
            onView(withId(R.id.home_list)).perform(scrollToPosition<RecyclerView.ViewHolder>(index))
            // check name text
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_name))
                .check(matches(withText(uiModel.name)))
            // check meta text
            onView(withRecyclerView(R.id.home_list).atPositionOnView(index, R.id.txt_meta))
                .check(matches(withText(uiModel.origin)))
        }
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun launchActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockHomeVmProvider.mModels.value = mockItems
        }
    }

    @After
    fun doAfterTest() {
        Intents.release()
    }

}
