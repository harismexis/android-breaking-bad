package com.example.breakingbad.datasource

import com.example.breakingbad.framework.datasource.database.BreakingBadLocalDao
import com.example.breakingbad.framework.datasource.database.BreakingBadLocalDataSource
import com.example.breakingbad.framework.extensions.toLocalItems
import com.example.breakingbad.framework.extensions.toLocalItem
import com.example.breakingbad.setup.UnitTestSetup
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito

@RunWith(JUnit4::class)
class BreakingBadLocalDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: BreakingBadLocalDao

    private lateinit var subject: BreakingBadLocalDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = BreakingBadLocalDataSource(mockDao)
    }

    @Test
    fun dataSourceInsertsItems_then_daoInsertsExpectedLocalItems() {
        runBlocking {
            // given
            val mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
            val mockLocalItems = mockItems.toLocalItems()

            // when
            subject.insertActors(mockItems)

            // then
            verify(mockDao, times(1)).insertActors(mockLocalItems)
        }
    }

    @Test
    fun dataSourceRequestsItem_then_daoRetrievesExpectedLocalItem() {
        runBlocking {
            // given
            val mockLocalItem = mockParser.getMockBBCharValid().toLocalItem()
            val mockItemId = mockLocalItem.char_id
            Mockito.`when`(mockDao.getActorById(mockItemId)).thenReturn(mockLocalItem)

            // when
            val item = subject.getActor(mockItemId)

            // then
            verify(mockDao, times(1)).getActorById(mockItemId)
        }
    }

    @Test
    fun dataSourceRequestsItems_then_daoRetrievesExpectedLocalItems() {
        runBlocking {
            // given
            val mockLocalItems = mockParser.getMockBBCharsLocalFromFeedWithAllItemsValid()
            Mockito.`when`(mockDao.getAllActors()).thenReturn(mockLocalItems)

            // when
            val items = subject.getAllActors()

            // then
            verify(mockDao, times(1)).getAllActors()
        }
    }

}