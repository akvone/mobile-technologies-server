package com.akvone.mobiletechnologies.converters

import com.akvone.mobiletechnologies.plain_objects.Measurement
import com.akvone.mobiletechnologies.plain_objects.MeasurementRequestDTO
import java.time.LocalDateTime

fun createMeasurement(id: String, requestDTO: MeasurementRequestDTO): Measurement {
    return Measurement(id, LocalDateTime.now(), requestDTO.bpm, requestDTO.lower, requestDTO.upper);
}