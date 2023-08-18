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

    fun findPaisByNome(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaises().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByNome(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisByRegiao(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaises().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegiao(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisByRegiaoIntermediaria(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaises().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegiaoIntermediaria(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisBySubRegiao(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaises().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findBySubRegiao(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

}
