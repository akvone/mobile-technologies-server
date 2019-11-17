package com.akvone.mobiletechnologies.services

import com.akvone.mobiletechnologies.exceptions.NotFoundException
import com.akvone.mobiletechnologies.plain_objects.Measurement
import org.springframework.stereotype.Service

@Service
class MainService {
    val measurementsStorage: MutableMap<Long, Measurement> = HashMap()

    fun createMeasurement(newMeasurement: Measurement) {
        measurementsStorage[newMeasurement.id] = newMeasurement
    }

    fun getMeasurement(id: Long): Measurement = measurementsStorage[id] ?: throw NotFoundException()
    fun getMeasurements(): List<Measurement> = measurementsStorage.values.toList()

}