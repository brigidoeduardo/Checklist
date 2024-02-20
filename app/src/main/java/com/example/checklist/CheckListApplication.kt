package com.example.checklist

import android.app.Application
import androidx.room.Room

class CheckListApplication : Application (){
    private lateinit var dataBase: AppDataBase

    override fun OnCreate () {
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