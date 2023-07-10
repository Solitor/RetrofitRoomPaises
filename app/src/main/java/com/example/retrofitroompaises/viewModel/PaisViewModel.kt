package com.example.retrofitroompaises.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitroompaises.data.AppDatabase
import com.example.retrofitroompaises.model.Pais
import com.example.retrofitroompaises.repository.PaisRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PaisViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PaisRepository
    private val allPais: LiveData<List<Pais>>

    init {
        val dao = AppDatabase.getDatabase(application).paisDao()
        repository = PaisRepository(dao)
        allPais = repository.allPais
    }

    fun insert(pais: Pais): Boolean = runBlocking {
        val result = CompletableDeferred<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = repository.insert(pais)
            result.complete(isSuccess)
        }
        result.await()
    }

    fun deleteAllPaises() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun getAllPaises(): LiveData<List<Pais>> {
        return repository.getAll()
    }

    fun findByNome(searchTarget: String): LiveData<List<Pais>>{
        return repository.findByNome(searchTarget)
    }
}