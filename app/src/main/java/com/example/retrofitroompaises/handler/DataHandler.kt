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
            paisViewModel.getAllPaisesByRegiao().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegiao(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }
    fun findPaisByRegiaoDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaisesByRegiaoDesc().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegiaoDesc(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisByRegiaoIntermediaria(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaisesByRegiaoIntermediaria().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegiaoIntermediaria(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisByRegiaoIntermediariaDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaisesByRegiaoIntermediariaDesc().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findByRegiaoIntermediariaDesc(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisBySubRegiao(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaisesBySubRegiao().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findBySubRegiao(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

    fun findPaisBySubRegiaoDesc(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllPaisesBySubRegiaoDesc().observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findBySubRegiaoDesc(busca).observe(viewLifecycleOwner, Observer { paisList ->
                adapter.updateData(paisList)
            })
        }
    }

}
