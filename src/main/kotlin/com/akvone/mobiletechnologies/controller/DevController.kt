package com.akvone.mobiletechnologies.controller

import com.akvone.mobiletechnologies.plain_object.Measurement
import com.akvone.mobiletechnologies.plain_object.UserRequestDTO
import com.akvone.mobiletechnologies.service.MainService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("dev")
class DevController(val mainService: MainService) {

    @DeleteMapping("/measurements")
    fun deleteMeasurements(): List<Measurement> {
        val measurements = mainService.getMeasurements()
        mainService.deleteMeasurements()

        return measurements
    }

    @PostMapping("/register")
    fun createUser(@RequestBody userRequestDTO: UserRequestDTO) {
        mainService.createUser(userRequestDTO);
    }
}