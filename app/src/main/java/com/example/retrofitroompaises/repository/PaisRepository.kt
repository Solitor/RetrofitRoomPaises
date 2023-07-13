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

    fun getAll(): LiveData<List<Pais>>{
        return paisDao.getAll()
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
}