package com.akvone.mobiletechnologies.controller

import com.akvone.mobiletechnologies.plain_object.MeasurementDTO
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
    fun getMeasurements(): List<MeasurementDTO> = mainService.getMeasurements()

    @GetMapping("/{id}")
    fun getMeasurement(@PathVariable id: String): MeasurementDTO = mainService.getMeasurement(id)
}