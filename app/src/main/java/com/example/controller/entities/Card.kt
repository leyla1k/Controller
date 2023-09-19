package com.example.controller.entities

import java.util.Date

data class Card(val id: Int,
                val tagId:String,
                val name: String,
                val surName: String,
                val patronymic: String?,
                val dateOfBirth: Date,
                val gender:Gender
                //,val snils:String
)

enum class Gender(){
    FEMALE, MALE
}