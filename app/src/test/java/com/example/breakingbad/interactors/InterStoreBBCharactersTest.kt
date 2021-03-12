package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterLocalRepository
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock

@RunWith(JUnit4::class)
class InterStoreBBCharactersTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: BBCharacterLocalRepository

    private lateinit var mockItems: List<BBCharacter>
    private lateinit var subject: InterStoreBBCharacters

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = InterStoreBBCharacters(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            subject.invoke(mockItems)

            // then
            verify(mockRepository, times(1)).insertItems(mockItems)
        }

}