package com.example.breakingbad.utils

import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemote
import org.junit.Assert

class BBCharacterItemRemoteVerificator {

    fun verifyBBCharactersAgainstRemoteItems(
        actual: List<BBCharacter>,
        expected: List<BBCharacterRemote?>
    ) {
        expected.forEachIndexed lit@{ _, remoteItem ->
            if (remoteItem == null) return@lit
            actual.forEachIndexed { _, item ->
                remoteItem.char_id?.let {
                    if (it == item.char_id) {
                        verifyBBCharacterAgainstRemoteItem(item, remoteItem)
                        return@lit
                    }
                }
            }
        }
    }

    private fun verifyBBCharacterAgainstRemoteItem(
        actual: BBCharacter,
        expected: BBCharacterRemote
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

