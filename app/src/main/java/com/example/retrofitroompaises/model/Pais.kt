package com.example.retrofitroompaises.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "pais_table")
data class Pais(
    @PrimaryKey val id : Int,

    @ColumnInfo val nome: String?,

    @SerializedName("regiao-intermediaria")
    @ColumnInfo val regiaoIntermediaria: String?,

    @SerializedName("sub-regiao")
    @ColumnInfo val subRegiao: String?,

    @SerializedName("regiao")
    @ColumnInfo val regiao: String?
) /*{
    constructor(
        nome: String?,
        regiaoIntermediaria: String?,
        subRegiao: String?,
        regiao: String?) : this(0, nome, regiaoIntermediaria,subRegiao,regiao)
}*/
