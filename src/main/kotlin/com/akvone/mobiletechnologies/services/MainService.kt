package com.akvone.mobiletechnologies.services

import com.akvone.mobiletechnologies.converters.convertToMeasurement
import com.akvone.mobiletechnologies.exceptions.NotFoundException
import com.akvone.mobiletechnologies.plain_objects.Measurement
import com.akvone.mobiletechnologies.plain_objects.MeasurementRequestDTO
import com.akvone.mobiletechnologies.repositories.MeasurementRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MainService(val measurementRepository: MeasurementRepository) {

    fun createMeasurement(requestDTO: MeasurementRequestDTO) {
        val measurement = convertToMeasurement(UUID.randomUUID().toString(), requestDTO)
        measurementRepository.save(measurement)
    }

    fun getMeasurement(id: String): Measurement = measurementRepository.findById(id).orElseThrow(::NotFoundException)
    fun getMeasurements(): List<Measurement> = measurementRepository.findAll()
    fun deleteMeasurements() = measurementRepository.deleteAll()

}