package com.example.retrofitroompaises.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.retrofitroompaises.model.Pais

@Dao
interface PaisDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(pais: Pais)

    @Query("SELECT * FROM pais_table ORDER BY nome COLLATE NOCASE ASC")
    fun getAll(): LiveData<List<Pais>>

    @Query("DELETE FROM pais_table")
    fun deleteAll()

    @Delete
    fun delete(pais: Pais)

    @Update
    fun update(pais: Pais)

    @Query("DELETE FROM pais_table WHERE id = :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM pais_table WHERE LOWER(nome) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY nome COLLATE NOCASE ASC")
    fun findByNome(searchTarget: String): LiveData<List<Pais>>
}