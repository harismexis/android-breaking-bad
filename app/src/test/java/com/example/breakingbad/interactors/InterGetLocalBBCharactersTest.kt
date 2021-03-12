package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterLocalRepository
import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class InterGetLocalBBCharactersTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: BBCharacterLocalRepository

    private lateinit var mockItems: List<BBCharacter>
    private lateinit var subject: InterGetLocalBBCharacters

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = InterGetLocalBBCharacters(mockRepository)
    }

    private fun setupMocks() {
        mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
        runBlocking {
            Mockito.`when`(mockRepository.getItems()).thenReturn(mockItems)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val items = subject.invoke()

            // then
            verify(mockRepository, times(1)).getItems()
            Assert.assertEquals(mockItems.size, items.size)
        }

}