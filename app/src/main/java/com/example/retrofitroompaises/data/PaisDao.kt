package com.example.retrofitroompaises.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.retrofitroompaises.model.Country

@Dao
interface PaisDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(country: Country)

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

    @Query("DELETE FROM country_table")
    fun deleteAll()

    @Delete
    fun delete(country: Country)

    @Update
    fun update(country: Country)

    @Query("DELETE FROM country_table WHERE id = :id")
    fun deleteById(id: Int)

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