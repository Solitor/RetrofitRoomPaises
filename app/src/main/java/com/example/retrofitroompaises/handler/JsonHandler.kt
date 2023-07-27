package com.example.retrofitroompaises.handler

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.retrofitroompaises.data.ApiService
import com.example.retrofitroompaises.model.Pais
import com.example.retrofitroompaises.viewModel.PaisViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JsonHandler(
    private val paisViewModel: PaisViewModel,
    private val apiService: ApiService,
    private val lifecycleScope: LifecycleCoroutineScope
) {

    fun resetJsonOperation() {
        //paisViewModel.deleteAllPaises()
        insertAllPaisJSONs()
    }

    private fun insertAllPaisJSONs() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getPaisesJSON()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.forEach {
                        val entity = Pais(
                            id = it.id.m49,
                            nome = it.nome,
                            regiaoIntermediaria = it.regiaoIntermediaria?.nome ?: "",
                            subRegiao = it.subRegiao.nome,
                            regiao = it.subRegiao.regiao.nome
                        )
                        paisViewModel.insert(entity)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("LOOKOUT_UAV", "API request unsuccessful: $errorBody")
                }
            } catch (e: Exception) {
                Log.e("LOOKOUT_UAV", "Exception occurred: ${e.message}", e)
            }
        }
    }
}