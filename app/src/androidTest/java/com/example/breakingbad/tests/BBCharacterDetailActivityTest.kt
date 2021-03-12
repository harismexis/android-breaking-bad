package com.example.breakingbad.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.breakingbad.R
import com.example.breakingbad.setup.base.InstrumentedTestSetup
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.setup.mockvm.MockBBCharacterDetailVmProvider
import com.example.breakingbad.presentation.bbcharacterdetail.viewmodel.BBCharacterDetailViewModel
import com.example.breakingbad.framework.extensions.wikipediaSpanned
import com.example.breakingbad.presentation.bbcharacterdetail.ui.BBCharacterDetailActivity
import io.mockk.every
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BBCharacterDetailActivityTest : InstrumentedTestSetup() {

    @get:Rule
    val testRule: ActivityTestRule<BBCharacterDetailActivity> =
        ActivityTestRule(
            BBCharacterDetailActivity::class.java,
            false, false
        )

    private lateinit var mockViewModel: BBCharacterDetailViewModel
    private lateinit var mockItem: BBCharacter

    @Before
    fun setup() {
        mockViewModel = MockBBCharacterDetailVmProvider.provideMockBBCharacterDetailVm()
        every { mockViewModel.retrieveItemById(any()) } returns Unit
        mockItem = mockParser.getMockBBCharValid()
    }

    @Test
    fun liveDataChanges_then_uiUpdatedWithExpectedData() {
        // given
        every { mockViewModel.model } returns MockBBCharacterDetailVmProvider.model
        launchActivityAndMockLiveData()

        // then
        onView(withId(R.id.img)).check(matches(isDisplayed()))

        onView(withId(R.id.txt_name_label)).check(matches(withText(getString(R.string.label_name))))
        onView(withId(R.id.txt_breed_name)).check(matches(withText(mockItem.name)))

        onView(withId(R.id.txt_birthday_label)).check(matches(withText(getString(R.string.label_birthday))))
        onView(withId(R.id.txt_birthday)).check(matches(withText(mockItem.origin)))

        onView(withId(R.id.txt_status_label)).check(matches(withText(getString(R.string.label_status))))
        onView(withId(R.id.txt_status)).check(matches(withText(mockItem.description)))

        onView(withId(R.id.txt_nickname_label)).check(matches(withText(getString(R.string.label_nickname))))
        onView(withId(R.id.txt_nickname)).check(matches(withText(mockItem.temperament)))

        onView(withId(R.id.txt_portrayed_label)).check(matches(withText(getString(R.string.label_portrayed))))
        onView(withId(R.id.txt_portrayed)).check(matches(withText(mockItem.lifeSpan)))

        onView(withId(R.id.txt_category_label)).check(matches(withText(getString(R.string.label_category))))
        onView(withId(R.id.txt_category)).check(matches(withText(mockItem.energyLevel.toString())))

        onView(withId(R.id.txt_wikipedia_url)).check(
            matches(
                withText(
                    mockItem.wikipediaSpanned(
                        getString(R.string.missing_img_url),
                        getString(R.string.character_image)
                    )
                        .toString()
                )
            )
        )
    }

    private fun launchActivityAndMockLiveData() {
        testRule.launchActivity(null)
        testRule.activity.runOnUiThread {
            MockBBCharacterDetailVmProvider.mModel.value = mockItem
        }
    }

    private fun getString(id: Int): String {
        return InstrumentationRegistry.getInstrumentation()
            .targetContext.resources.getString(id)
    }

}
