package com.example.retrofitroompaises.handler

import android.util.Log
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.retrofitroompaises.data.ApiService
import com.example.retrofitroompaises.model.Country
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
                val response = apiService.getCountryJSON()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    responseBody?.forEach {
                        val entity = Country(
                            name =  it.name.common,
                            official = it.name.official,
                            acronym = it.cca3,
                            capital = it.capital?.firstOrNull(),
                            region = it.region,
                            subregion = it.subregion,
                            area = it.area,
                            population = it.population,
                            continent = it.continents.firstOrNull()
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