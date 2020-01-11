package com.akvone.mobiletechnologies.plain_object

import java.time.LocalDateTime

data class MeasurementRequestDTO(val bpm: Int, val lower: Int, val upper: Int)
data class MeasurementDTO(val id: String, val createdAt: LocalDateTime,
                          val bpm: Int, val lower: Int, val upper: Int)

data class UserRequestDTO(var username: String, val password: String)

data class ProfileDTO(val name: String, val age: Int?, val height: Int?, val weight: Double?)