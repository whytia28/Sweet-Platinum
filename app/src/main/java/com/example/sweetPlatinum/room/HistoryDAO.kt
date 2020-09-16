package com.example.sweetPlatinum.room

import androidx.room.*
import com.example.sweetPlatinum.menuActivity.ui.history.HistoryFragment

@Dao
interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(historyFragment: HistoryFragment): Long

    @Query("SELECT * FROM history")
    fun read(): List<HistoryFragment>

    @Update
    fun update(memo: HistoryFragment): Int

    @Delete
    fun delete(memo: HistoryFragment): Int
}