package com.harismexis.breakingbad.interactors

import com.harismexis.breakingbad.data.BreakingBadLocalRepository
import com.harismexis.breakingbad.domain.Actor
import com.harismexis.breakingbad.interactors.actor.IrrGetLocalActor
import com.harismexis.breakingbad.setup.UnitTestSetup
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
class IrrGetLocalActorTest : UnitTestSetup() {

    @Mock
    private lateinit var mockRepository: BreakingBadLocalRepository

    private lateinit var mockItem: Actor
    private var mockItemId: Int = 0
    private lateinit var subject: IrrGetLocalActor

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        setupMocks()
        subject = IrrGetLocalActor(mockRepository)
    }

    private fun setupMocks() {
        mockItem = mockParser.getMockBBCharValid()
        mockItemId = mockItem.char_id
        runBlocking {
            Mockito.`when`(mockRepository.getActor(mockItemId)).thenReturn(mockItem)
        }
    }

    @Test
    fun interactorInvoked_then_repositoryCallsExpectedMethodWithExpectedArgAndResult() =
        runBlocking {
            // when
            val item = subject(mockItemId)

            // then
            verify(mockRepository, times(1)).getActor(mockItemId)
            Assert.assertEquals(mockItem.char_id, item!!.char_id)
        }

}