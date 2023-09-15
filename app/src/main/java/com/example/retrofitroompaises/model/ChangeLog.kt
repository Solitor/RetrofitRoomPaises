package com.example.retrofitroompaises.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "change_log")
data class ChangeLog(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,

    @ColumnInfo val timestamp: Long,
    @ColumnInfo val countryId: Int,
    @ColumnInfo val operation: String,
    val before: String? = null,
    val after: String? = null
)