package com.example.checklist

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Item (
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val description : String
) : Serializable