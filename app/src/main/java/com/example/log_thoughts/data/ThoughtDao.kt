package com.example.log_thoughts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ThoughtDao {
    @Query("SELECT * FROM Thought ORDER BY id DESC")
    fun getAll(): List<Thought>

    @Insert
    fun insertAll(vararg logs: Thought)

    @Query("DELETE FROM Thought")
    fun nukeTable()

}