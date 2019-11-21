package com.akvone.mobiletechnologies.controller

import com.akvone.mobiletechnologies.plain_object.Measurement
import com.akvone.mobiletechnologies.plain_object.MeasurementRequestDTO
import com.akvone.mobiletechnologies.service.MainService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/measurements")
class MeasurementController(val mainService: MainService) {

    @PostMapping
    fun createMeasurement(@RequestBody newMeasurement: MeasurementRequestDTO) {
        mainService.createMeasurement(newMeasurement)
    }

    @GetMapping
    fun getMeasurements(): List<Measurement> = mainService.getMeasurements()

    @GetMapping("/{id}")
    fun getMeasurement(@PathVariable id: String): Measurement = mainService.getMeasurement(id)
}