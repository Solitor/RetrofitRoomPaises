package com.example.retrofitroompaises.data

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.retrofitroompaises.model.ChangeLog
import com.example.retrofitroompaises.model.Country
import com.google.gson.Gson

@Dao
interface PaisDao {
    fun countryToJson(country: Country): String {
        val gson = Gson()
        return gson.toJson(country)
    }
    fun jsonToCountry(jsonString: String): Country? {
        val gson = Gson()
        return gson.fromJson(jsonString, Country::class.java)
    }

    // === Insert Operations =======================================================================
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(country: Country)

    @Insert
    fun insertChangeLog(changeLog: ChangeLog)

    fun insertAndLog(country: Country) : Boolean {
        return try {
            insert(country)
            val countryInserted = getCountryByName(country.name)
            insertChangeLog(
                ChangeLog(
                    timestamp = System.currentTimeMillis(),
                    countryId = countryInserted.id!!,
                    operation = "INSERT",
                    after = countryToJson(countryInserted)
                )
            )
            true
        } catch (ex: SQLiteConstraintException) {
            // Duplicate ID, handle the conflict here
            Log.e("insertError", "Duplicate ID")
            false
        }
    }


    // === Update Operations =======================================================================
    @Update
    fun update(country: Country)

    fun updateAndLog(country: Country){
        val beforeState = getCountryById(country.id!!)
        update(country)
        insertChangeLog(
            ChangeLog(
                timestamp = System.currentTimeMillis(),
                countryId = country.id,
                operation = "UPDATE",
                before = countryToJson(beforeState),
                after = countryToJson(country)
            )
        )
    }


    // === Delete Operations =======================================================================
    @Delete
    fun delete(country: Country)

    fun deleteAndLog(country: Country) : Boolean {
        return try {
            val beforeState = getCountryById(country.id!!)
            delete(country)
            insertChangeLog(
                ChangeLog(
                    timestamp = System.currentTimeMillis(),
                    countryId = country.id,
                    operation = "DELETE",
                    before = countryToJson(beforeState)
                )
            )
            true
        } catch (ex: SQLiteConstraintException) {
            // Duplicate ID, handle the conflict here
            Log.e("deleteError", "ID not found")
            false
        }
    }

    @Query("DELETE FROM country_table WHERE id = :id")
    fun deleteById(id: Int)

    fun deleteByIdAndLog(id: Int) : Boolean {
        return try {
            val beforeState = getCountryById(id)
            deleteById(id)
            insertChangeLog(
                ChangeLog(
                    timestamp = System.currentTimeMillis(),
                    countryId = id,
                    operation = "DELETE",
                    before = countryToJson(beforeState)
                )
            )
            true
        } catch (ex: SQLiteConstraintException) {
            // Duplicate ID, handle the conflict here
            Log.e("deleteError", "ID not found")
            false
        }
    }

    @Query("DELETE FROM country_table")
    fun deleteAll()

    @Query("DELETE FROM change_log")
    fun deleteAllChangeLog()

    fun deleteAllAndLog(){
        deleteAll()
        deleteAllChangeLog()
    }


    // === Get Operations ==========================================================================
    @Query("SELECT * FROM country_table ORDER BY name COLLATE NOCASE ASC")
    fun getAll(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY name COLLATE NOCASE DESC")
    fun getAllDesc(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY id COLLATE NOCASE ASC")
    fun getAllById(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY id COLLATE NOCASE DESC")
    fun getAllByIdDesc(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY region COLLATE NOCASE ASC, name COLLATE NOCASE ASC")
    fun getAllByRegion(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY region COLLATE NOCASE DESC, name COLLATE NOCASE ASC")
    fun getAllByRegionDesc(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY continent COLLATE NOCASE ASC, name COLLATE NOCASE ASC")
    fun getAllByContinent(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY continent COLLATE NOCASE DESC, name COLLATE NOCASE ASC")
    fun getAllByContinentDesc(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY subregion COLLATE NOCASE ASC, name COLLATE NOCASE ASC")
    fun getAllBySubRegion(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table ORDER BY subregion COLLATE NOCASE DESC, name COLLATE NOCASE ASC")
    fun getAllBySubRegionDesc(): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE id = :id")
    fun getCountryById(id: Int): Country

    @Query("SELECT * FROM country_table WHERE name = :name")
    fun getCountryByName(name:String): Country


    // === Find Operations =========================================================================
    @Query("SELECT * FROM country_table WHERE LOWER(name) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY name COLLATE NOCASE ASC")
    fun findByName(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(id) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY id COLLATE NOCASE ASC")
    fun findById(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(id) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY id COLLATE NOCASE DESC")
    fun findByIdDesc(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(region) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY name COLLATE NOCASE ASC")
    fun findByRegion(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(region) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY name COLLATE NOCASE DESC")
    fun findByRegionDesc(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(continent) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY name COLLATE NOCASE ASC")
    fun findByContinent(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(continent) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY name COLLATE NOCASE DESC")
    fun findByContinentDesc(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(subregion) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY name COLLATE NOCASE ASC")
    fun findBySubRegion(searchTarget: String): LiveData<List<Country>>

    @Query("SELECT * FROM country_table WHERE LOWER(subregion) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY name COLLATE NOCASE DESC")
    fun findBySubRegionDesc(searchTarget: String): LiveData<List<Country>>
}