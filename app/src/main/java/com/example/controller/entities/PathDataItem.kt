package com.example.controller.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "paths"/*, foreignKeys = [
    ForeignKey(
        entity = WorkShiftDataItem::class,
        parentColumns = ["id"],
        childColumns = ["workId"],
        onDelete = ForeignKey.CASCADE
    )]*/)
data class PathDataItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val workId: Int,
    val govNumber: String,
    val pathNumber:String,
    val startDate: Long,
    var endDate: Long?
)

