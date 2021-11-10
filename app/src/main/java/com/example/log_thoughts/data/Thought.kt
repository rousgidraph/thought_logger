package com.example.log_thoughts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Thought( val msg: String , val timestamp: Long) {
    @PrimaryKey(autoGenerate = true)
    var id:Long =0


}