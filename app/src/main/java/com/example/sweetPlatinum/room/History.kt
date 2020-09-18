package com.example.sweetPlatinum.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class History(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "history") var history: String,
    @ColumnInfo(name = "mode") var mode: String

) : Parcelable