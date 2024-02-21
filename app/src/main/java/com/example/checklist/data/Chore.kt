package com.example.checklist.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Chore (
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val description : String
) : Serializable