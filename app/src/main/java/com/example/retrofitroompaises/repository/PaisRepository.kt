package com.example.retrofitroompaises.repository

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.retrofitroompaises.data.PaisDao
import com.example.retrofitroompaises.model.ChangeLog
import com.example.retrofitroompaises.model.Country

class PaisRepository(private val paisDao: PaisDao) {

    val allPais: LiveData<List<Country>> = paisDao.getAll()

    // === Insert Operations =======================================================================
    fun insert(country: Country): Boolean {
        return paisDao.insertAndLog(country)
    }


    // === Update Operations =======================================================================
    fun update(country : Country){
        paisDao.updateAndLog(country)
    }


    // === Delete Operations =======================================================================
    fun delete(country: Country): Boolean {
        return paisDao.deleteAndLog(country)
    }

    fun deleteById(id: Int): Boolean {
        return paisDao.deleteByIdAndLog(id)
    }

    fun deleteAll(){
        paisDao.deleteAllAndLog()
    }


    // === Get Operations ==========================================================================
    fun getAll(): LiveData<List<Country>>{
        return paisDao.getAll()
    }
    fun getAllDesc(): LiveData<List<Country>>{
        return paisDao.getAllDesc()
    }
    fun getAllByRegion(): LiveData<List<Country>>{
        return paisDao.getAllByRegion()
    }
    fun getAllByRegionDesc(): LiveData<List<Country>>{
        return paisDao.getAllByRegionDesc()
    }
    fun getAllByContinent(): LiveData<List<Country>>{
        return paisDao.getAllByContinent()
    }
    fun getAllByContinentDesc(): LiveData<List<Country>>{
        return paisDao.getAllByContinentDesc()
    }
    fun getAllBySubRegion(): LiveData<List<Country>>{
        return paisDao.getAllBySubRegion()
    }
    fun getAllBySubRegionDesc(): LiveData<List<Country>>{
        return paisDao.getAllBySubRegionDesc()
    }
    fun getAllById(): LiveData<List<Country>>{
        return paisDao.getAllById()
    }
    fun getAllByIdDesc(): LiveData<List<Country>>{
        return paisDao.getAllByIdDesc()
    }

    fun getAllChangeLogNewest(): LiveData<List<ChangeLog>>{
        return paisDao.getAllChangeLogNewest()
    }

    fun getAllChangeLogOldest(): LiveData<List<ChangeLog>>{
        return paisDao.getAllChangeLogOldest()
    }

    fun getAllChangeLogNewestOperation(operationTarget: String): LiveData<List<ChangeLog>>{
        return paisDao.getAllChangeLogNewestOperation(operationTarget)
    }

    fun getAllChangeLogOldestOperation(operationTarget: String): LiveData<List<ChangeLog>>{
        return paisDao.getAllChangeLogOldestOperation(operationTarget)
    }


    // === Find Operations =========================================================================
    fun findByName(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findByName(searchTarget)
    }
    fun findById(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findById(searchTarget)
    }
    fun findByIdDesc(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findByIdDesc(searchTarget)
    }
    fun findByRegion(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findByRegion(searchTarget)
    }
    fun findByRegionDesc(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findByRegionDesc(searchTarget)
    }
    fun findByContinent(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findByContinent(searchTarget)
    }
    fun findByContinentDesc(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findByContinentDesc(searchTarget)
    }
    fun findBySubRegion(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findBySubRegion(searchTarget)
    }
    fun findBySubRegionDesc(searchTarget: String): LiveData<List<Country>> {
        return paisDao.findBySubRegionDesc(searchTarget)
    }

    fun findChangeLogByIdNewest(searchTarget: String):LiveData<List<ChangeLog>>{
        return paisDao.findChangeLogByIdNewest(searchTarget)
    }

    fun findChangeLogByIdOldest(searchTarget: String):LiveData<List<ChangeLog>>{
        return paisDao.findChangeLogByIdOldest(searchTarget)
    }

    fun findChangeLogByIdNewestOperation(searchTarget: String,operationTarget: String):LiveData<List<ChangeLog>>{
        return paisDao.findChangeLogByIdNewestOperation(searchTarget, operationTarget)
    }

    fun findChangeLogByIdOldestOperation(searchTarget: String,operationTarget: String):LiveData<List<ChangeLog>>{
        return paisDao.findChangeLogByIdOldestOperation(searchTarget, operationTarget)
    }
}