package com.example.breakingbad.datasource

import com.example.breakingbad.framework.datasource.network.data.BreakingBadRemoteDao
import com.example.breakingbad.framework.datasource.network.data.BBCharacterRemoteDataSource
import com.example.breakingbad.setup.UnitTestSetup
import com.example.breakingbad.utils.BBCharacterItemRemoteVerificator
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class BBCharacterRemoteDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: BreakingBadRemoteDao

    private var verificator = BBCharacterItemRemoteVerificator()

    private lateinit var subject: BBCharacterRemoteDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = BBCharacterRemoteDataSource(mockDao)
    }

    @Test
    fun dateSourceRequestsItems_then_daoRequestsItems() {
        // when
        runBlocking {
            // given
            val mockFeed = mockParser.getMockBBCharsFeedAllIdsValid()
            Mockito.`when`(mockDao.getBreakingBadCharacters()).thenReturn(mockFeed)

            // when
            val items = subject.getItems()

            // then
            verify(mockDao, times(1)).getBreakingBadCharacters()
            verificator.verifyBBCharactersAgainstRemoteItems(items, mockFeed)
        }
    }

}