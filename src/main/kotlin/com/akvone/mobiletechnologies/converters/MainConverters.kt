package com.akvone.mobiletechnologies.converters

import com.akvone.mobiletechnologies.plain_objects.Measurement
import com.akvone.mobiletechnologies.plain_objects.MeasurementRequestDTO

fun convertToMeasurement(id: String, requestDTO: MeasurementRequestDTO): Measurement {
    return Measurement(id, requestDTO.bpm);
}