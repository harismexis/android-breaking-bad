package com.example.breakingbad.framework.datasource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breaking_bad_actor_table")
data class BBActorLocal(
    @PrimaryKey @ColumnInfo(name = "char_id") val char_id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "birthday") val birthday: String?,
    @ColumnInfo(name = "img") val img: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "nickname") val nickname: String?,
    @ColumnInfo(name = "portrayed") val portrayed: String?,
    @ColumnInfo(name = "category") val category: String?
)
