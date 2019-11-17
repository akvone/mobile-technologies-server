package com.akvone.mobiletechnologies.controllers

import com.akvone.mobiletechnologies.plain_objects.Measurement
import com.akvone.mobiletechnologies.services.MainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class MainController {

    @Autowired
    lateinit var mainService: MainService

    @PostMapping("/measurement")
    fun createMeasurement(@RequestBody newMeasurement: Measurement) {
        mainService.createMeasurement(newMeasurement);
    }

    @GetMapping("/measurements")
    fun getMeasurement(): List<Measurement> {
        val measurements = mainService.getMeasurements()

        return measurements
    }

    @GetMapping("/measurement")
    fun getMeasurement(@PathVariable id: Long): Measurement {
        val measurement = mainService.getMeasurement(id)

        return measurement
    }
}