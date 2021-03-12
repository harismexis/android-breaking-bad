package com.example.breakingbad.datasource

import com.example.breakingbad.framework.datasource.database.BBLocalDao
import com.example.breakingbad.framework.datasource.database.BBCharacterLocalDataSource
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
class BBCharacterLocalDataSourceTest : UnitTestSetup() {

    @Mock
    private lateinit var mockDao: BBLocalDao

    private lateinit var subject: BBCharacterLocalDataSource

    init {
        initialise()
    }

    override fun initialiseClassUnderTest() {
        subject = BBCharacterLocalDataSource(mockDao)
    }

    @Test
    fun dataSourceInsertsItems_then_daoInsertsExpectedLocalItems() {
        runBlocking {
            // given
            val mockItems = mockParser.getMockBBCharsFromFeedWithAllItemsValid()
            val mockLocalItems = mockItems.toLocalItems()

            // when
            subject.insert(mockItems)

            // then
            verify(mockDao, times(1)).insertItems(mockLocalItems)
        }
    }

    @Test
    fun dataSourceRequestsItem_then_daoRetrievesExpectedLocalItem() {
        runBlocking {
            // given
            val mockLocalItem = mockParser.getMockBBCharValid().toLocalItem()
            val mockItemId = mockLocalItem.char_id
            Mockito.`when`(mockDao.getItemById(mockItemId)).thenReturn(mockLocalItem)

            // when
            val item = subject.getItem(mockItemId)

            // then
            verify(mockDao, times(1)).getItemById(mockItemId)
        }
    }

    @Test
    fun dataSourceRequestsItems_then_daoRetrievesExpectedLocalItems() {
        runBlocking {
            // given
            val mockLocalItems = mockParser.getMockBBCharsLocalFromFeedWithAllItemsValid()
            Mockito.`when`(mockDao.getAllItems()).thenReturn(mockLocalItems)

            // when
            val items = subject.getAll()

            // then
            verify(mockDao, times(1)).getAllItems()
        }
    }

}