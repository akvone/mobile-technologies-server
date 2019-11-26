package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.repository.MeasurementRepository
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Profile("dev")
@Service
class DevOnlyService(val measurementRepository: MeasurementRepository) {

    fun deleteMeasurements() = measurementRepository.deleteAll()

}