package com.example.checklist

import androidx.room.Database




    @Database (
        entities = [Item::class], version = 1
    )
    abstract class AppDataBase : RoomDataBase () {
        abstract fun itemDao (): ItemDao
    }