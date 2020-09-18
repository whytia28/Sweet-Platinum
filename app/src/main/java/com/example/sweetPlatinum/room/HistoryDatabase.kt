package com.example.sweetPlatinum.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDAO() : HistoryDAO

    companion object {
        private var INSTANCE: HistoryDatabase? = null

        fun getInstance(context: Context): HistoryDatabase? {
            if (INSTANCE == null) {
                synchronized(HistoryDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        HistoryDatabase::class.java, "history.db")
                        .build()
                }
            }
            return INSTANCE
        }

    }
}