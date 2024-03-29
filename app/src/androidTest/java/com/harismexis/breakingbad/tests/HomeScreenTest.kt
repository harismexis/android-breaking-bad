package com.harismexis.breakingbad.tests

import android.view.Gravity
import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
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
import com.harismexis.breakingbad.core.domain.Actor
import com.harismexis.breakingbad.core.result.ActorsResult
import com.harismexis.breakingbad.mocks.mockHomeViewModel
import com.harismexis.breakingbad.presentation.screens.activity.MainActivity
import com.harismexis.breakingbad.util.RecyclerCountAssertion
import com.harismexis.breakingbad.util.SearchViewActionExtension
import com.harismexis.breakingbad.util.verifyRecyclerItemAt
import io.mockk.every
import org.hamcrest.CoreMatchers.not
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

    private fun launchComponentUnderTest(): ActivityScenario<MainActivity> {
        return launchActivity()
    }

    @Test
    fun actorsFeedHasAllItemsValid_listHasExpectedItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid())

        // when
        launchComponentUnderTest()

        // then
        verifyRecycler()
    }

    @Test
    fun actorsFeedHasSomeInvalidIds_listHasExpectedItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasSomeInvalidIds())

        // when
        launchComponentUnderTest()

        // then
        verifyRecycler()
    }

    @Test
    fun actorsFeedHasSomeEmptyActorJsonItems_listHasExpectedItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasSomeEmptyItems())

        // when
        launchComponentUnderTest()

        // then
        verifyRecycler()
    }

    @Test
    fun actorsFeedHasAllIdsInvalid_listHasNoItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllIdsInvalid())

        // when
        launchComponentUnderTest()

        // then
        verifyRecyclerEmpty()
    }

    @Test
    fun actorsFeedIsEmptyJson_listHasNoItems() {
        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonIsEmpty())

        // when
        launchComponentUnderTest()

        // then
        verifyRecyclerEmpty()
    }

    @Test
    fun userSearchesActorByName_listHasExpectedItems() {
        // Test Initial Results

        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid())
        // when
        launchComponentUnderTest()
        // then
        verifyRecycler()

        // Search by actor name "walter" and check results

        // given
        mockSearchResults(WALTER, mockActorsProvider.getMockActorsSearchByNameLikeWalter())
        // when
        onView(withId(R.id.searchView)).perform(SearchViewActionExtension.submitQuery(WALTER))
        // then
        verifyRecycler()

        // Search by actor name "sala" and check results

        // given
        mockSearchResults(SALA, mockActorsProvider.getMockActorsSearchByNameLikeSala())
        // when
        onView(withId(R.id.searchView)).perform(SearchViewActionExtension.submitQuery(SALA))
        // then
        verifyRecycler()
    }

    @Test
    fun navigationDrawer_showsExpectedItems() {

        // given
        mockInitialResults(mockActorsProvider.getMockActorsWhenJsonHasAllItemsValid())

        // when
        launchComponentUnderTest()

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
        launchComponentUnderTest()

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
        every { mockHomeViewModel.updateActors() } answers {
            mockActorsResult.value = actorsSuccess
        }
        every { mockHomeViewModel.actors } returns mockActorsResult
    }

    private fun mockSearchResults(actorName: String, mockData: List<Actor>) {
        mockActors = mockData
        actorsSuccess = ActorsResult.Success(mockActors)
        every { mockHomeViewModel.searchActors(actorName) } answers {
            mockActorsResult.value = actorsSuccess
        }
        every { mockHomeViewModel.actors } returns mockActorsResult
    }

    private fun verifyRecycler() {
        verifyRecyclerVisibility(true)
        verifyRecyclerData()
    }

    private fun verifyRecyclerEmpty() {
        verifyRecyclerVisibility(false)
        verifyRecyclerData()
    }

    private fun verifyRecyclerVisibility(
        visible: Boolean
    ) {
        if (visible) onView(withId(R.id.home_list)).check(matches(isDisplayed()))
        else onView(withId(R.id.home_list)).check(matches(not(isDisplayed())))
    }

    private fun verifyRecyclerData() {
        verifyRecyclerCount()
        verifyRecyclerRows()
    }

    private fun verifyRecyclerCount() =
        onView(withId(R.id.home_list)).check(RecyclerCountAssertion(mockActors.size))

    private fun verifyRecyclerRows() {
        mockActors.forEachIndexed { index, actor ->
            onView(withId(R.id.home_list)).perform(scrollToPosition<RecyclerView.ViewHolder>(index))
            verifyRecyclerValue(index, R.id.txt_title, actor.nickname)
            verifyRecyclerValue(index, R.id.txt_meta, actor.name)
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