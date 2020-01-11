package com.akvone.mobiletechnologies.develop

import com.akvone.mobiletechnologies.controller.AuthController
import com.akvone.mobiletechnologies.exception.UserAlreadyExistsException
import com.akvone.mobiletechnologies.plain_object.UserRequestDTO
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@Profile("dev")
@Configuration
class DevConfiguration(val authController: AuthController) {

    @EventListener
    fun onContextRefreshedEvent(event: ContextRefreshedEvent) {
        try {
            authController.createUser(UserRequestDTO("akvone", "admin"))
        } catch (e: UserAlreadyExistsException) {

        }
    }
}