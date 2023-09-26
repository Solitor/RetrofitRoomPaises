package com.example.retrofitroompaises.handler

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.example.retrofitroompaises.viewModel.LogAdapter
import com.example.retrofitroompaises.viewModel.PaisIdAdapter
import com.example.retrofitroompaises.viewModel.PaisViewModel

class DataLogHandler(
    private val paisViewModel: PaisViewModel,
    private val adapter: LogAdapter,
    private val viewLifecycleOwner: LifecycleOwner
) {

    fun findChangeLogByIdNewest(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllChangeLogNewest().observe(viewLifecycleOwner, Observer { changeLogList ->
                adapter.updateData(changeLogList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findChangeLogByIdNewest(busca).observe(viewLifecycleOwner, Observer { changeLogList->
                adapter.updateData(changeLogList)
            })
        }
    }

    fun findChangeLogByIdOldest(busca: String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllChangeLogOldest().observe(viewLifecycleOwner, Observer { changeLogList ->
                adapter.updateData(changeLogList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findChangeLogByIdOldest(busca).observe(viewLifecycleOwner, Observer { changeLogList ->
                adapter.updateData(changeLogList)
            })
        }
    }

    fun findChangeLogByIdNewestOperation(busca: String, operation : String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllChangeLogNewestOperation(operation).observe(viewLifecycleOwner, Observer { changeLogList ->
                adapter.updateData(changeLogList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findChangeLogByIdNewestOperation(busca, operation).observe(viewLifecycleOwner, Observer { changeLogList->
                adapter.updateData(changeLogList)
            })
        }
    }

    fun findChangeLogByIdOldestOperation(busca: String, operation : String) {
        if (busca.isEmpty()) {
            paisViewModel.getAllChangeLogNewestOperation(operation).observe(viewLifecycleOwner, Observer { changeLogList ->
                adapter.updateData(changeLogList) // Update the adapter's data when the list changes
            })
        } else {
            paisViewModel.findChangeLogByIdNewestOperation(busca, operation).observe(viewLifecycleOwner, Observer { changeLogList->
                adapter.updateData(changeLogList)
            })
        }
    }
}