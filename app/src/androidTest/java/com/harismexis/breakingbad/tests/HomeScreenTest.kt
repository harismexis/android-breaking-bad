package com.harismexis.breakingbad.tests

import android.view.Gravity
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.DrawerMatchers.isClosed
import androidx.test.espresso.contrib.DrawerMatchers.isOpen
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.harismexis.breakingbad.R
import com.harismexis.breakingbad.base.BaseInstrumentedTest
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_ALL_IDS_VALID
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_NO_DATA
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_SALA
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_WALTER
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_SOME_EMPTY
import com.harismexis.breakingbad.mocks.MockActorsProvider.Companion.NUM_ACTORS_WHEN_SOME_IDS_INVALID
import com.harismexis.breakingbad.mocks.mockHomeViewModel
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.result.ActorsResult
import com.harismexis.breakingbad.presentation.screens.activity.MainActivity
import com.harismexis.breakingbad.util.RecyclerCountAssertion
import com.harismexis.breakingbad.util.SearchViewActionExtension
import com.harismexis.breakingbad.util.verifyRecyclerItemAt
import io.mockk.every
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest : BaseInstrumentedTest() {

    companion object {
        const val WALTER = "walter"
        const val SALA = "sala"
    }

    private lateinit var mockActors: List<Actor>
    private lateinit var actorsSuccess: ActorsResult.Success
    private val mockActorsResult = MutableLiveData<ActorsResult>()

    @Test
    fun actorsFeedHasAllItemsValid_listHasExpectedItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        verifyRecycler(NUM_ACTORS_WHEN_ALL_IDS_VALID)
    }

    @Test
    fun actorsFeedHasSomeInvalidIds_listHasExpectedItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasSomeInvalidIds())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        verifyRecycler(NUM_ACTORS_WHEN_SOME_IDS_INVALID)
    }

    @Test
    fun actorsFeedHasSomeEmptyActorJsonItems_listHasExpectedItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasSomeEmptyItems())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        verifyRecycler(NUM_ACTORS_WHEN_SOME_EMPTY)
    }

    @Test
    fun actorsFeedHasAllIdsInvalid_listHasNoItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllIdsInvalid())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        verifyRecycler(NUM_ACTORS_WHEN_NO_DATA)
    }

    @Test
    fun actorsFeedIsEmptyJson_listHasNoItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonIsEmpty())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        verifyRecycler(NUM_ACTORS_WHEN_NO_DATA)
    }

    @Test
    fun userSearchesActorByName_listHasExpectedItems() {
        // Test Initial Results

        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid())
        // when
        val scenario = launchActivity<MainActivity>()
        // then
        verifyRecycler(NUM_ACTORS_WHEN_ALL_IDS_VALID)

        // Search by actor name "walter" and check results

        // given
        mockSearchResults(WALTER, mockActorsProvider.getMockActorsSearchByNameLikeWalter())
        // when
        onView(withId(R.id.searchView)).perform(SearchViewActionExtension.submitQuery(WALTER))
        // then
        verifyRecycler(NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_WALTER)

        // Search by actor name "sala" and check results

        // given
        mockSearchResults(SALA, mockActorsProvider.getMockActorsSearchByNameLikeSala())
        // when
        onView(withId(R.id.searchView)).perform(SearchViewActionExtension.submitQuery(SALA))
        // then
        verifyRecycler(NUM_ACTORS_WHEN_SEARCH_BY_NAME_LIKE_SALA)
    }

    @Test
    fun navigationDrawer_showsExpectedItems() {

        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        onView(withId(R.id.home_drawer_layout))
            .check(matches(isClosed(Gravity.LEFT)))
            .perform(DrawerActions.open())
            .check(matches(isOpen()))

        onView(withId(R.id.nav_view))
            .check(matches(isDisplayed()))
            .check(matches(hasDescendant(withText(R.string.quotes))))
            .check(matches(hasDescendant(withId(R.id.quotes_dest))))
            .check(matches(hasDescendant(withText(R.string.episodes))))
            .check(matches(hasDescendant(withId(R.id.episodes_dest))))
            .check(matches(hasDescendant(withText(R.string.deaths))))
            .check(matches(hasDescendant(withId(R.id.deaths_dest))))
            .check(matches(hasDescendant(withText(R.string.player))))
            .check(matches(hasDescendant(withId(R.id.player_dest))))
            .check(matches(hasDescendant(withText(R.string.map))))
            .check(matches(hasDescendant(withId(R.id.map_dest))))
            .check(matches(hasDescendant(withText(R.string.api))))
            .check(matches(hasDescendant(withId(R.id.doc_dest))))
    }

    @Test
    fun bottomNavigationView_showsExpectedItems() {

        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        onView(withId(R.id.bottom_nav_view))
            .check(matches(isDisplayed()))
            .check(matches(hasDescendant(withText(R.string.quotes))))
            .check(matches(hasDescendant(withId(R.id.quotes_dest))))
            .check(matches(hasDescendant(withText(R.string.episodes))))
            .check(matches(hasDescendant(withId(R.id.episodes_dest))))
            .check(matches(hasDescendant(withText(R.string.deaths))))
            .check(matches(hasDescendant(withId(R.id.deaths_dest))))
    }

    private fun mockInitialResults(mockData: List<Actor>) {
        mockActors = mockData
        actorsSuccess = ActorsResult.Success(mockActors)
        every { mockHomeViewModel.fetchActors() } answers {
            mockActorsResult.value = actorsSuccess
        }
        every { mockHomeViewModel.actorsResult } returns mockActorsResult
    }

    private fun mockSearchResults(actorName: String, mockData: List<Actor>) {
        mockActors = mockData
        actorsSuccess = ActorsResult.Success(mockActors)
        every { mockHomeViewModel.updateSearchQuery(actorName) } answers {
            mockActorsResult.value = actorsSuccess
        }
        every { mockHomeViewModel.actorsResult } returns mockActorsResult
    }

    private fun verifyRecycler(expectedNumberOfItems: Int) {
        onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        verifyRecyclerCount(expectedNumberOfItems)
        verifyRecyclerData()
    }

    private fun verifyRecyclerCount(expectedNumberOfItems: Int) {
        Assert.assertEquals(actorsSuccess.items.size, expectedNumberOfItems)
        onView(withId(R.id.home_list)).check(RecyclerCountAssertion(expectedNumberOfItems))
    }

    private fun verifyRecyclerData() {
        mockActors.forEachIndexed { index, actor ->
            onView(withId(R.id.home_list)).perform(scrollToPosition<RecyclerView.ViewHolder>(index))
            verifyRecyclerValue(index, R.id.txt_name, actor.name)
            verifyRecyclerValue(index, R.id.txt_meta, actor.nickname)
        }
    }

    private fun verifyRecyclerValue(
        index: Int,
        @IdRes viewId: Int,
        value: String?
    ) {
        verifyRecyclerItemAt(R.id.home_list, index, viewId, value)
    }

}