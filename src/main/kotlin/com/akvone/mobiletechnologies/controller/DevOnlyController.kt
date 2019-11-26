package com.akvone.mobiletechnologies.controller

import com.akvone.mobiletechnologies.service.DevOnlyService
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Profile("dev")
@RestController
@RequestMapping("dev")
class DevOnlyController(val devOnlyService: DevOnlyService) {

    @DeleteMapping("/measurements")
    fun deleteMeasurements() {
        devOnlyService.deleteMeasurements()
    }
}