package com.akvone.mobiletechnologies.controller

import com.akvone.mobiletechnologies.plain_object.ProfileDTO
import com.akvone.mobiletechnologies.service.MainService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/profile")
class ProfileController(val mainService: MainService) {

    @PostMapping
    fun updateProfile(@RequestBody profileDTO: ProfileDTO) = mainService.updateProfile(profileDTO)

    @GetMapping
    fun getProfile(): ProfileDTO = mainService.getProfile()

}
