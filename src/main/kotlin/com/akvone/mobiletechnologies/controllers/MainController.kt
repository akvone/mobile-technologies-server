package com.akvone.mobiletechnologies.controllers

import com.akvone.mobiletechnologies.plain_objects.Measurement
import com.akvone.mobiletechnologies.plain_objects.MeasurementRequestDTO
import com.akvone.mobiletechnologies.services.MainService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class MainController(val mainService: MainService) {

    @PostMapping("/measurement")
    fun createMeasurement(@RequestBody newMeasurement: MeasurementRequestDTO) {
        mainService.createMeasurement(newMeasurement)
    }

    @GetMapping("/measurements")
    fun getMeasurement(): List<Measurement> = mainService.getMeasurements()

    @GetMapping("/measurement")
    fun getMeasurement(@PathVariable id: String): Measurement = mainService.getMeasurement(id)
}