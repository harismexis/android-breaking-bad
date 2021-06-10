package com.harismexis.breakingbad.framework.extensions.death

import com.harismexis.breakingbad.model.domain.Death
import com.harismexis.breakingbad.model.datasource.database.table.LocalDeath

fun List<LocalDeath?>?.toItems(): List<Death> {
    val items = mutableListOf<Death>()
    if (this == null) return items.toList()
    val filteredList = this.filterNotNull()
    items.addAll(filteredList.map {
        it.toItem()
    })
    return items.toList()
}

fun LocalDeath.toItem(): Death {
    return Death(
        this.death_id,
        this.death,
        this.cause,
        this.responsible,
        this.last_words,
        this.season,
        this.episode,
        this.number_of_deaths
    )
}

fun List<Death?>?.toLocalItems(): List<LocalDeath> {
    val localItems = mutableListOf<LocalDeath>()
    if (this == null) return localItems.toList()
    val filteredList = this.filterNotNull()
    localItems.addAll(filteredList.map {
        it.toLocalItem()
    })
    return localItems.toList()
}

fun Death.toLocalItem(): LocalDeath {
    return LocalDeath(
        this.death_id,
        this.death,
        this.cause,
        this.responsible,
        this.last_words,
        this.season,
        this.episode,
        this.number_of_deaths
    )
}
