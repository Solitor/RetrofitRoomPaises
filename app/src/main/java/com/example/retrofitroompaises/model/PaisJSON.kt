package com.example.retrofitroompaises.model


import com.google.gson.annotations.SerializedName

data class PaisJSON(
    @SerializedName("id")
    val id: Id,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("regiao-intermediaria")
    val regiaoIntermediaria: RegiaoIntermediaria?,
    @SerializedName("sub-regiao")
    val subRegiao: SubRegiao
)
{
    data class Id(
        @SerializedName("ISO-ALPHA-2")
        val iSOALPHA2: String,
        @SerializedName("ISO-ALPHA-3")
        val iSOALPHA3: String,
        @SerializedName("M49")
        val m49: Int
    )

    data class RegiaoIntermediaria(
        @SerializedName("id")
        val id: Id,
        @SerializedName("nome")
        val nome: String
    ) {
        data class Id(
            @SerializedName("M49")
            val m49: Int
        )
    }
    data class SubRegiao(
        @SerializedName("id")
        val id: Id,
        @SerializedName("nome")
        val nome: String,
        @SerializedName("regiao")
        val regiao: Regiao
    ) {
        data class Id(
            @SerializedName("M49")
            val m49: Int
        )

        data class Regiao(
            @SerializedName("id")
            val id: Id,
            @SerializedName("nome")
            val nome: String
        ) {
            data class Id(
                @SerializedName("M49")
                val m49: Int
            )
        }
    }
}