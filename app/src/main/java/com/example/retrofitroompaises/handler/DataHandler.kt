package com.example.retrofitroompaises.handler

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.retrofitroompaises.viewModel.PaisAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel

class DataHandler(
    private val paisViewModel: PaisViewModel,
    private val adapter: PaisAdapter,
    private val viewLifecycleOwner: LifecycleOwner
) {

    fun findCountryByName(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountries().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByName(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }

    fun findCountryByRegion(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesByRegion().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegion(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }
    fun findCountryByRegionDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesByRegionDesc().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegionDesc(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }

    fun findCountryByContinent(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesByContinent().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByContinent(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }

    fun findCountryByContinentDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesByContinentDesc().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByContinentDesc(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }

    fun findCountryBySubRegion(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesBySubRegion().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findBySubRegion(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }

    fun findCountryBySubRegionDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllCountriesBySubRegionDesc().observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findBySubRegionDesc(busca).observe(viewLifecycleOwner, Observer { countryList ->
                adapter.updateData(countryList)
            })
        }
    }

}
