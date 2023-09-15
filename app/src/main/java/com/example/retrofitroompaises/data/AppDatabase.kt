package com.example.retrofitroompaises.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitroompaises.model.ChangeLog
import com.example.retrofitroompaises.model.Country

@Database(entities = [Country::class, ChangeLog::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun paisDao(): PaisDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "pais_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}