package com.example.controller.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "workshifts"
)
data class WorkShiftDataItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val startDate: Long,
    var endDate: Long?,
)