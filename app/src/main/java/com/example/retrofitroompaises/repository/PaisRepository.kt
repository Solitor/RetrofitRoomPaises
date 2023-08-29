package com.example.retrofitroompaises.repository

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.retrofitroompaises.data.PaisDao
import com.example.retrofitroompaises.model.Pais

class PaisRepository(private val paisDao: PaisDao) {

    val allPais: LiveData<List<Pais>> = paisDao.getAll()

    fun insert(pais: Pais): Boolean {
        return try {
            paisDao.insert(pais)
            // Insertion successful, no duplicate ID
            true
        } catch (e: SQLiteConstraintException) {
            // Duplicate ID, handle the conflict here
            Log.e("insertError", "Duplicate ID")
            false
        }
    }

    fun update(pais : Pais){
        paisDao.update(pais)
    }

    fun getAll(): LiveData<List<Pais>>{
        return paisDao.getAll()
    }
    fun getAllDesc(): LiveData<List<Pais>>{
        return paisDao.getAllDesc()
    }

    fun getAllByRegiao(): LiveData<List<Pais>>{
        return paisDao.getAllByRegiao()
    }
    fun getAllByRegiaoDesc(): LiveData<List<Pais>>{
        return paisDao.getAllByRegiaoDesc()
    }

    fun getAllByRegiaoIntermediaria(): LiveData<List<Pais>>{
        return paisDao.getAllByRegiaoIntermediaria()
    }
    fun getAllByRegiaoIntermediariaDesc(): LiveData<List<Pais>>{
        return paisDao.getAllByRegiaoIntermediariaDesc()
    }

    fun getAllBySubRegiao(): LiveData<List<Pais>>{
        return paisDao.getAllBySubRegiao()
    }
    fun getAllBySubRegiaoDesc(): LiveData<List<Pais>>{
        return paisDao.getAllBySubRegiaoDesc()
    }

    fun getAllById(): LiveData<List<Pais>>{
        return paisDao.getAllById()
    }

    fun getAllByIdDesc(): LiveData<List<Pais>>{
        return paisDao.getAllByIdDesc()
    }

    fun deleteAll(){
        paisDao.deleteAll()
    }

    fun delete(pais: Pais): Boolean {
        return try {
            paisDao.delete(pais)
            // Insertion successful, no duplicate ID
            true
        } catch (e: SQLiteConstraintException) {
            // Duplicate ID, handle the conflict here
            Log.e("deleteError", "Pais not found")
            false
        }
    }

    fun deleteById(id: Int): Boolean {
        return try {
            paisDao.deleteById(id)
            // Insertion successful, no duplicate ID
            true
        } catch (e: SQLiteConstraintException) {
            // Duplicate ID, handle the conflict here
            Log.e("deleteError", "ID not found")
            false
        }
    }

    fun findByNome(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findByNome(searchTarget)
    }

    fun findById(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findById(searchTarget)
    }

    fun findByIdDesc(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findByIdDesc(searchTarget)
    }


    fun findByRegiao(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findByRegiao(searchTarget)
    }
    fun findByRegiaoDesc(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findByRegiaoDesc(searchTarget)
    }

    fun findByRegiaoIntermediaria(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findByRegiaoIntermediaria(searchTarget)
    }
    fun findByRegiaoIntermediariaDesc(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findByRegiaoIntermediariaDesc(searchTarget)
    }

    fun findBySubRegiao(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findBySubRegiao(searchTarget)
    }
    fun findBySubRegiaoDesc(searchTarget: String): LiveData<List<Pais>> {
        return paisDao.findBySubRegiaoDesc(searchTarget)
    }
}