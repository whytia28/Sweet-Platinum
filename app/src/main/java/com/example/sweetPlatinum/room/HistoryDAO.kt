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
    fun update(memo: History): Int

    @Delete
    fun delete(memo: History): Int
}