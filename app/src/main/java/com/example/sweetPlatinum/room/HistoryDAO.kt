package com.example.sweetPlatinum.room

import androidx.room.*
import com.example.sweetPlatinum.menuActivity.ui.history.HistoryFragment

@Dao
interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(historyBattle: History): Long

    @Query("SELECT * FROM history")
    fun read(): List<History>

    @Update
    fun update(historyBattle: History ): Int

    @Delete
    fun delete(historyBattle: History): Int

    @Delete
    fun deleteAll(listHistory: List<History>):Int
}