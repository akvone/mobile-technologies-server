package com.akvone.mobiletechnologies.controller

import com.akvone.mobiletechnologies.service.DevOnlyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("dev")
class DevOnlyController(val devOnlyService: DevOnlyService) { // TODO: Remove later

    @DeleteMapping("/measurements")
    fun deleteMeasurements() {
        devOnlyService.deleteMeasurements()
    }
}