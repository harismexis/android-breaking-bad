package com.example.jsonfeed.presentation.detail.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.jsonfeed.R
import com.example.jsonfeed.instrumentedsetup.base.InstrumentedTestSetup
import com.example.jsonfeed.domain.Item
import com.example.jsonfeed.instrumentedsetup.mockproviders.MockItemDetailVmProvider
import com.example.jsonfeed.presentation.detail.viewmodel.ItemDetailVm
import io.mockk.every
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemDetailActivityTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<ItemDetailActivity> =
        ActivityTestRule(
            ItemDetailActivity::class.java,
            false, false
        )

    private lateinit var mockItemDetailVm: ItemDetailVm
    private lateinit var mockItem: Item

    @Before
    fun setup() {
        mockItemDetailVm = MockItemDetailVmProvider.provideMockItemDetailVm()
        every { mockItemDetailVm.retrieveItemById(any()) } returns Unit
        mockItem = mockParser.getMockItemValid()
    }

    @Test
    fun liveDataChanges_uiIsUpdatedWithCorrectData() {
        // given
        every { mockItemDetailVm.model } returns MockItemDetailVmProvider.model
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.img)).check(matches(isDisplayed()))
        onView(withId(R.id.txt_name)).check(matches(withText(mockItem.name)))
        onView(withId(R.id.txt_supertype_label)).check(matches(withText(getString(R.string.label_supertype))))
        onView(withId(R.id.txt_supertype)).check(matches(withText(mockItem.supertype)))
        onView(withId(R.id.txt_subtype_label)).check(matches(withText(getString(R.string.label_subtype))))
        onView(withId(R.id.txt_subtype)).check(matches(withText(mockItem.subtype)))
        onView(withId(R.id.txt_artist_label)).check(matches(withText(getString(R.string.label_artist))))
        onView(withId(R.id.txt_artist)).check(matches(withText(mockItem.artist)))
        onView(withId(R.id.txt_rarity_label)).check(matches(withText(getString(R.string.label_rarity))))
        onView(withId(R.id.txt_rarity)).check(matches(withText(mockItem.rarity)))
        onView(withId(R.id.txt_series_label)).check(matches(withText(getString(R.string.label_series))))
        onView(withId(R.id.txt_series)).check(matches(withText(mockItem.series)))
        onView(withId(R.id.txt_set_label)).check(matches(withText(getString(R.string.label_set))))
        onView(withId(R.id.txt_set)).check(matches(withText(mockItem.set)))
        onView(withId(R.id.txt_set_code_label)).check(matches(withText(getString(R.string.label_set_code))))
        onView(withId(R.id.txt_set_code)).check(matches(withText(mockItem.setCode)))
    }

    private fun launchActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockItemDetailVmProvider.mModel.value = mockItem
        }
    }

    private fun getString(id: Int): String {
        val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
        return targetContext.resources.getString(id)
    }

}
