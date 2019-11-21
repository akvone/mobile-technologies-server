package com.akvone.mobiletechnologies.plain_object

data class MeasurementRequestDTO(val bpm: Int, val lower: Int, val upper: Int)
data class UserRequestDTO(val username: String, val password: String)