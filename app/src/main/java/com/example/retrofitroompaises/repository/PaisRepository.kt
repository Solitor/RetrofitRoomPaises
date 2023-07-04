package com.example.retrofitroompaises.repository

import androidx.lifecycle.LiveData
import com.example.retrofitroompaises.data.PaisDao
import com.example.retrofitroompaises.model.Pais

class PaisRepository(private val paisDao: PaisDao) {

    val allPais: LiveData<List<Pais>> = paisDao.getAll()

    fun insert(pais: Pais){
        paisDao.insert(pais)
    }

    fun getAll(): LiveData<List<Pais>>{
        return paisDao.getAll()
    }

    fun deleteAll(){
        paisDao.deleteAll()
    }

    fun delete(pais: Pais){
        paisDao.delete(pais)
    }

    fun findByNome(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findByNome(searchTarget)
    }
}