package com.example.checklist

import android.app.Application
import androidx.room.Room
import com.example.checklist.data.AppDataBase

class CheckListApplication:Application (){
    private lateinit var dataBase: AppDataBase

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "checklist-database"
        ).build()
    }
    fun getAppDataBase (): AppDataBase {
        return dataBase
    }
}