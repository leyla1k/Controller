package com.example.controller.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cards")
data class CardDataItem(
    @PrimaryKey
    val id: Int,
    val tagId: String,
    val name: String,
    val surName: String,
    val patronymic: String,
    val dateOfBirth: Long,
    val gender: Boolean
)