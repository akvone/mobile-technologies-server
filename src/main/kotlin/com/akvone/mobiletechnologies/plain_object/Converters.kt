package com.akvone.mobiletechnologies.plain_object

/**
 * File provides simple methods for conversion, which don't have any business logic
 */

fun Profile.convertToDto(): ProfileDTO {
    return ProfileDTO(name, age, height, weight)
}

fun Measurement.convertToDto(): MeasurementDTO {
    return MeasurementDTO(id, createdAt, bpm, lower, upper)
}