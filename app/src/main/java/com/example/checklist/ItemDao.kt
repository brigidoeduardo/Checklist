package com.example.checklist

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (item:Item)

    @Query("SELECT * FROM item")
    fun getAll(): LiveData<List<Item>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update (item:Item)

    @Query ("DELETE FROM item WHERE id=:id")
    fun deleteById (id:Int)
}