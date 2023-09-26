package com.example.retrofitroompaises.handler

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.retrofitroompaises.viewModel.PaisIdAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel

class DataIdHandler(
    private val paisViewModel: PaisViewModel,
    private val adapter: PaisIdAdapter,
    private val viewLifecycleOwner: LifecycleOwner
) {

    fun findCountryById(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesById().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findById(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }

    fun findCountryByIdDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesByIdDesc().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByIdDesc(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }
}