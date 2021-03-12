package com.example.breakingbad.utils

import com.example.breakingbad.domain.BBCharacter
import com.example.breakingbad.framework.datasource.network.model.BBCharacterRemoteItem
import org.junit.Assert

class BBCharacterItemRemoteVerificator {

    fun verifyBBCharactersAgainstRemoteItems(
        actual: List<BBCharacter>,
        expected: List<BBCharacterRemoteItem?>
    ) {
        expected.forEachIndexed lit@{ _, remoteItem ->
            if (remoteItem == null) return@lit
            actual.forEachIndexed { _, item ->
                remoteItem.id?.let {
                    if (it == item.id) {
                        verifyBBCharacterAgainstRemoteItem(item, remoteItem)
                        return@lit
                    }
                }
            }
        }
    }

    private fun verifyBBCharacterAgainstRemoteItem(
        actual: BBCharacter,
        expected: BBCharacterRemoteItem
    ) {
        Assert.assertEquals(expected.id, actual.id)
        Assert.assertEquals(expected.name, actual.name)
        Assert.assertEquals(expected.temperament, actual.temperament)
        Assert.assertEquals(expected.origin, actual.origin)
        Assert.assertEquals(expected.description, actual.description)
        Assert.assertEquals(expected.lifeSpan, actual.lifeSpan)
        Assert.assertEquals(expected.energyLevel, actual.energyLevel)
        Assert.assertEquals(expected.wikipediaUrl, actual.wikipediaUrl)
        Assert.assertEquals(expected.image?.url, actual.imageUrl)
    }

}

