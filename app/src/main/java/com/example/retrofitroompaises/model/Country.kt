package com.example.retrofitroompaises.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "country_table")
data class Country(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,

    @SerializedName("nome")
    @ColumnInfo val name: String,

    @SerializedName("official_name")
    @ColumnInfo val official: String?,

    @SerializedName("cca3")
    @ColumnInfo val acronym: String?,

    @SerializedName("capital")
    @ColumnInfo val capital: String?,

    @SerializedName("region")
    @ColumnInfo val region: String?,

    @SerializedName("subregion")
    @ColumnInfo val subregion: String?,

    @SerializedName("area")
    @ColumnInfo val area: Double?,

    @SerializedName("population")
    @ColumnInfo val population: Int?,

    @SerializedName("continents")
    @ColumnInfo val continent: String?

)
