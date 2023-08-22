package com.example.retrofitroompaises.handler

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.retrofitroompaises.viewModel.PaisAdapter
import com.example.retrofitroompaises.viewModel.PaisIdAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel

class DataIdHandler(
    private val paisViewModel: PaisViewModel,
    private val adapter: PaisIdAdapter,
    private val viewLifecycleOwner: LifecycleOwner
) {

    fun findPaisById(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaisesById().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findById(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisByIdDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaisesByIdDesc().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByIdDesc(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }
}