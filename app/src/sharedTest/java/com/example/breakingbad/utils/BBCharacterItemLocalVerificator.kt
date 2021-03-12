package com.example.breakingbad.utils

import com.example.breakingbad.framework.datasource.database.BBCharacterLocal
import com.example.breakingbad.domain.BBCharacter
import org.junit.Assert

class BBCharacterItemLocalVerificator {

    fun verifyLocalItemsAgainstBBCharacters(
        actual: List<BBCharacterLocal>,
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
        expected: List<BBCharacterLocal>
    ) {
        verifyListsHaveSameSize(actual, expected)
        expected.forEachIndexed { index, localItem ->
            val item = actual[index]
            verifyBBCharacterAgainstLocalItem(item, localItem)
        }
    }

    private fun verifyBBCharacterAgainstLocalItem(
        actual: BBCharacter,
        expected: BBCharacterLocal
    ) {
        Assert.assertEquals(expected.char_id, actual.char_id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.birthday, actual.birthday)
        Assert.assertEquals(expected.img, actual.img)
        Assert.assertEquals(expected.status, actual.status)
        Assert.assertEquals(expected.nickname, actual.nickname)
        Assert.assertEquals(expected.portrayed, actual.portrayed)
        Assert.assertEquals(expected.category, actual.category)
    }

    private fun verifyLocalItemAgainstBBCharacter(
        actual: BBCharacterLocal,
        expected: BBCharacter
    ) {
        Assert.assertEquals(expected.char_id, actual.char_id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.birthday, actual.birthday)
        Assert.assertEquals(expected.img, actual.img)
        Assert.assertEquals(expected.status, actual.status)
        Assert.assertEquals(expected.nickname, actual.nickname)
        Assert.assertEquals(expected.portrayed, actual.portrayed)
        Assert.assertEquals(expected.category, actual.category)
    }

}

