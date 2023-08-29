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

    @Query("SELECT * FROM pais_table ORDER BY nome COLLATE NOCASE DESC")
    fun getAllDesc(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY id COLLATE NOCASE ASC")
    fun getAllById(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY id COLLATE NOCASE DESC")
    fun getAllByIdDesc(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY regiao COLLATE NOCASE ASC, nome COLLATE NOCASE ASC")
    fun getAllByRegiao(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY regiao COLLATE NOCASE DESC, nome COLLATE NOCASE ASC")
    fun getAllByRegiaoDesc(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY regiaoIntermediaria COLLATE NOCASE ASC, nome COLLATE NOCASE ASC")
    fun getAllByRegiaoIntermediaria(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY regiaoIntermediaria COLLATE NOCASE DESC, nome COLLATE NOCASE ASC")
    fun getAllByRegiaoIntermediariaDesc(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY subRegiao COLLATE NOCASE ASC, nome COLLATE NOCASE ASC")
    fun getAllBySubRegiao(): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table ORDER BY subRegiao COLLATE NOCASE DESC, nome COLLATE NOCASE ASC")
    fun getAllBySubRegiaoDesc(): LiveData<List<Pais>>

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

    @Query("SELECT * FROM pais_table WHERE LOWER(id) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY id COLLATE NOCASE ASC")
    fun findById(searchTarget: String): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table WHERE LOWER(id) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY id COLLATE NOCASE DESC")
    fun findByIdDesc(searchTarget: String): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table WHERE LOWER(regiao) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY nome COLLATE NOCASE ASC")
    fun findByRegiao(searchTarget: String): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table WHERE LOWER(regiao) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY nome COLLATE NOCASE DESC")
    fun findByRegiaoDesc(searchTarget: String): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table WHERE LOWER(regiaoIntermediaria) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY nome COLLATE NOCASE ASC")
    fun findByRegiaoIntermediaria(searchTarget: String): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table WHERE LOWER(regiaoIntermediaria) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY nome COLLATE NOCASE DESC")
    fun findByRegiaoIntermediariaDesc(searchTarget: String): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table WHERE LOWER(subRegiao) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY nome COLLATE NOCASE ASC")
    fun findBySubRegiao(searchTarget: String): LiveData<List<Pais>>

    @Query("SELECT * FROM pais_table WHERE LOWER(subRegiao) LIKE '%' || LOWER(:searchTarget) || '%' ORDER BY nome COLLATE NOCASE DESC")
    fun findBySubRegiaoDesc(searchTarget: String): LiveData<List<Pais>>
}