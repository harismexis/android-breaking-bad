package com.example.breakingbad.utils

import com.example.breakingbad.framework.datasource.database.BBCharacterLocalItem
import com.example.breakingbad.domain.BBCharacter
import org.junit.Assert

class BBCharacterItemLocalVerificator {

    fun verifyLocalItemsAgainstBBCharacters(
        actual: List<BBCharacterLocalItem>,
        expected: List<BBCharacter>
    ) {
        verifyListsHaveSameSize(actual, expected)
        expected.forEachIndexed { index, item ->
            val localItem = actual[index]
            verifyLocalItemAgainstBBCharacter(localItem, item)
        }
    }

    fun verifyBBCharactersAgainstLocalItems(
        actual: List<BBCharacter>,
        expected: List<BBCharacterLocalItem>
    ) {
        verifyListsHaveSameSize(actual, expected)
        expected.forEachIndexed { index, localItem ->
            val item = actual[index]
            verifyBBCharacterAgainstLocalItem(item, localItem)
        }
    }

    private fun verifyBBCharacterAgainstLocalItem(
        actual: BBCharacter,
        expected: BBCharacterLocalItem
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.temperament, actual.temperament)
        Assert.assertEquals(expected.origin, actual.origin)
        Assert.assertEquals(expected.description, actual.description)
        Assert.assertEquals(expected.lifeSpan, actual.lifeSpan)
        Assert.assertEquals(expected.energyLevel, actual.energyLevel)
        Assert.assertEquals(expected.wikipediaUrl, actual.wikipediaUrl)
        Assert.assertEquals(expected.imageUrl, actual.imageUrl)
    }

    private fun verifyLocalItemAgainstBBCharacter(
        actual: BBCharacterLocalItem,
        expected: BBCharacter
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.temperament, actual.temperament)
        Assert.assertEquals(expected.origin, actual.origin)
        Assert.assertEquals(expected.description, actual.description)
        Assert.assertEquals(expected.lifeSpan, actual.lifeSpan)
        Assert.assertEquals(expected.energyLevel, actual.energyLevel)
        Assert.assertEquals(expected.wikipediaUrl, actual.wikipediaUrl)
        Assert.assertEquals(expected.imageUrl, actual.imageUrl)
    }

}

