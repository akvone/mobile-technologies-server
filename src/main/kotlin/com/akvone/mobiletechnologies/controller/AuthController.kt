package com.akvone.mobiletechnologies.controller

import com.akvone.mobiletechnologies.plain_object.UserRequestDTO
import com.akvone.mobiletechnologies.service.MainService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/security")
class AuthController(val mainService: MainService) {
    @PostMapping("/register")
    fun createUser(@RequestBody userRequestDTO: UserRequestDTO) {
        mainService.createUser(userRequestDTO)
    }
}