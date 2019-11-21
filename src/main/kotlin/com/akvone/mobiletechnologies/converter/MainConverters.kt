package com.akvone.mobiletechnologies.converter

import com.akvone.mobiletechnologies.plain_object.Measurement
import com.akvone.mobiletechnologies.plain_object.MeasurementRequestDTO
import java.time.LocalDateTime

fun createMeasurement(id: String, requestDTO: MeasurementRequestDTO): Measurement {
    return Measurement(id, LocalDateTime.now(), requestDTO.bpm, requestDTO.lower, requestDTO.upper);
}