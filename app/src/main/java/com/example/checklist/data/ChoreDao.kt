package com.example.checklist.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ChoreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (chore: Chore)

    @Query("SELECT * FROM chore")
    fun getAll(): LiveData<List<Chore>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update (chore: Chore)

    @Query ("DELETE FROM chore WHERE id=:id")
    fun deleteById (id:Int)
}