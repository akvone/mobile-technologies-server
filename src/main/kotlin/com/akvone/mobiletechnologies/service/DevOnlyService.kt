package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.repositorie.MeasurementRepository
import org.springframework.stereotype.Service

@Service
class DevOnlyService(val measurementRepository: MeasurementRepository) { // TODO: Remove later

    fun deleteMeasurements() = measurementRepository.deleteAll()

}