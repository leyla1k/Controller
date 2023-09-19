package com.example.controller.entities

import java.util.Date

data class Path(val id: Int,
                val workId: Int,
                val govNumber:String,
                val pathNumber:String,
                //val userId:Int,
                val startDate: Date,
                var endDate:Date?
                )