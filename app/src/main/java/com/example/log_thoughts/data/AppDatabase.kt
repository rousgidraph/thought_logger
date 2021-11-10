package com.example.log_thoughts.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Thought::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ThoughtDao(): ThoughtDao

}