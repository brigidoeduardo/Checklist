package com.example.checklist.data

import androidx.room.Database
import androidx.room.RoomDatabase


    @Database (
        entities = [Chore::class], version = 1
    )
    abstract class AppDataBase: RoomDatabase () {
        abstract fun choreDao (): ChoreDao
    }