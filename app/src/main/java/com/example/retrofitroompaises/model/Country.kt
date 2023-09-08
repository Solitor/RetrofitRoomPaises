package com.example.retrofitroompaises.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "country_table")
data class Country(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,

    @SerializedName("nome")
    @ColumnInfo val name: String,       //country.name.common

    @SerializedName("official_name")
    @ColumnInfo val official: String?,  //country.name.official

    @SerializedName("cca3")
    @ColumnInfo val acronym: String?,   //country.cca3

    @SerializedName("capital")
    @ColumnInfo val capital: String?,   //country.capital?.firstOrNull()

    @SerializedName("region")
    @ColumnInfo val region: String?,    //country.region

    @SerializedName("subregion")
    @ColumnInfo val subregion: String?, //country.subregion

    @SerializedName("area")
    @ColumnInfo val area: Double?,      //country.area

    @SerializedName("population")
    @ColumnInfo val population: Int?,   //country.population

    @SerializedName("continents")
    @ColumnInfo val continent: String?, //country.continents.firstOrNull()

)
