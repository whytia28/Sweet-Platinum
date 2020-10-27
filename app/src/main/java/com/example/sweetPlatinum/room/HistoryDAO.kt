package com.example.sweetPlatinum.room

import androidx.room.*

@Dao
interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(historyBattle: History)

    @Query("SELECT * FROM history")
    fun read():List<History>

    @Update
    fun update(historyBattle: History): Int

    @Delete
    fun delete(historyBattle: History): Int

    @Query("DELETE  FROM history")
    fun deleteAll()
}