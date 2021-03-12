package com.example.breakingbad.interactors

import com.example.breakingbad.data.BBCharacterRemoteRepository
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
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class InterGetRemoteBBCharactersTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: BBCharacterRemoteRepository

    private lateinit var mockItems: List<BBCharacter>
    private lateinit var subject: InterGetRemoteBBCharacters

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        subject = InterGetRemoteBBCharacters(mockRepository)
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