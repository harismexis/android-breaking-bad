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
class InterGetLocalBBCharacterTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: BBCharacterLocalRepository

    private lateinit var mockItem: BBCharacter
    private lateinit var mockItemId: String
    private lateinit var subject: InterGetLocalBBCharacter

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = InterGetLocalBBCharacter(mockRepository)
    }

    private fun setupMocks() {
        mockItem = mockParser.getMockBBCharValid()
        mockItemId = mockItem.id
        runBlocking {
            Mockito.`when`(mockRepository.getItem(mockItemId)).thenReturn(mockItem)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val item = subject.invoke(mockItemId)

            // then
            verify(mockRepository, times(1)).getItem(mockItemId)
            Assert.assertEquals(mockItem.id, item!!.id)
        }

}