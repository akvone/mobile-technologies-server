package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.converter.createMeasurement
import com.akvone.mobiletechnologies.exception.NotFoundException
import com.akvone.mobiletechnologies.plain_object.Measurement
import com.akvone.mobiletechnologies.plain_object.MeasurementRequestDTO
import com.akvone.mobiletechnologies.repositorie.MeasurementRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MainService(val measurementRepository: MeasurementRepository) {

    fun createMeasurement(requestDTO: MeasurementRequestDTO) {
        val measurement = createMeasurement(UUID.randomUUID().toString(), requestDTO)
        measurementRepository.save(measurement)
    }

    fun getMeasurement(id: String): Measurement = measurementRepository.findById(id).orElseThrow(::NotFoundException)
    fun getMeasurements(): List<Measurement> = measurementRepository.findAll()
    fun deleteMeasurements() = measurementRepository.deleteAll()

}