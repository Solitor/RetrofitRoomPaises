package com.example.retrofitroompaises.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofitroompaises.data.AppDatabase
import com.example.retrofitroompaises.model.Country
import com.example.retrofitroompaises.model.Pais
import com.example.retrofitroompaises.repository.PaisRepository
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class PaisViewModel(application: Application): AndroidViewModel(application) {

    private val repository: PaisRepository
    private val allPais: LiveData<List<Country>>

    init {
        val dao = AppDatabase.getDatabase(application).paisDao()
        repository = PaisRepository(dao)
        allPais = repository.allPais
    }

    fun insert(country: Country): Boolean = runBlocking {
        val result = CompletableDeferred<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = repository.insert(country)
            result.complete(isSuccess)
        }
        result.await()
    }

    fun update(country:Country) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(country)
    }

    fun delete(country: Country): Boolean = runBlocking {
        val result = CompletableDeferred<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = repository.delete(country)
            result.complete(isSuccess)
        }
        result.await()
    }

    fun deleteById(id: Int): Boolean = runBlocking {
        val result = CompletableDeferred<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            val isSuccess = repository.deleteById(id)
            result.complete(isSuccess)
        }
        result.await()
    }

    fun deleteAllCountries() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun getAllCountries(): LiveData<List<Country>> {
        return repository.getAll()
    }

    fun getAllCountriesDesc(): LiveData<List<Country>> {
        return repository.getAllDesc()
    }

    fun getAllCountriesByRegion(): LiveData<List<Country>> {
        return repository.getAllByRegion()
    }

    fun getAllCountriesByRegionDesc(): LiveData<List<Country>> {
        return repository.getAllByRegionDesc()
    }

    fun getAllCountriesByContinent(): LiveData<List<Country>> {
        return repository.getAllByContinent()
    }

    fun getAllCountriesByContinentDesc(): LiveData<List<Country>> {
        return repository.getAllByContinentDesc()
    }

    fun getAllCountriesBySubRegion(): LiveData<List<Country>> {
        return repository.getAllBySubRegion()
    }

    fun getAllCountriesBySubRegionDesc(): LiveData<List<Country>> {
        return repository.getAllBySubRegionDesc()
    }

    fun getAllCountriesById(): LiveData<List<Country>> {
        return repository.getAllById()
    }

    fun getAllCountriesByIdDesc(): LiveData<List<Country>> {
        return repository.getAllByIdDesc()
    }

    fun findByName(searchTarget: String): LiveData<List<Country>>{
        return repository.findByName(searchTarget)
    }

    fun findById(searchTarget: String): LiveData<List<Country>>{
        return repository.findById(searchTarget)
    }

    fun findByIdDesc(searchTarget: String): LiveData<List<Country>>{
        return repository.findByIdDesc(searchTarget)
    }

    fun findByRegion(searchTarget: String): LiveData<List<Country>> {
        return repository.findByRegion(searchTarget)
    }
    fun findByRegionDesc(searchTarget: String): LiveData<List<Country>> {
        return repository.findByRegionDesc(searchTarget)
    }

    fun findByContinent(searchTarget: String): LiveData<List<Country>> {
        return repository.findByContinent(searchTarget)
    }
    fun findByContinentDesc(searchTarget: String): LiveData<List<Country>> {
        return repository.findByContinentDesc(searchTarget)
    }

    fun findBySubRegion(searchTarget: String): LiveData<List<Country>> {
        return repository.findBySubRegion(searchTarget)
    }
    fun findBySubRegionDesc(searchTarget: String): LiveData<List<Country>> {
        return repository.findBySubRegionDesc(searchTarget)
    }

}