package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.exception.EntityNotFoundException
import com.akvone.mobiletechnologies.exception.UserAlreadyExistsException
import com.akvone.mobiletechnologies.plain_object.Measurement
import com.akvone.mobiletechnologies.plain_object.MeasurementRequestDTO
import com.akvone.mobiletechnologies.plain_object.User
import com.akvone.mobiletechnologies.plain_object.UserRequestDTO
import com.akvone.mobiletechnologies.repositorie.MeasurementRepository
import com.akvone.mobiletechnologies.repositorie.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MainService(val measurementRepository: MeasurementRepository,
                  val userRepository: UserRepository,
                  val passwordEncoder: PasswordEncoder) {

    fun createMeasurement(requestDTO: MeasurementRequestDTO): Measurement {
        val userId = getCurrentUserId()
        val convertToEntity = convertToEntity(requestDTO, userId)

        return measurementRepository.save(convertToEntity)
    }

    fun getMeasurement(id: String): Measurement {
        val userId = getCurrentUserId()

        return measurementRepository.findByIdAndUserId(id, userId) ?: throw EntityNotFoundException()
    }

    fun getMeasurements(): List<Measurement> {
        val userId = getCurrentUserId()

        return measurementRepository.findByUserId(userId)
    }

    fun createUser(userRequestDTO: UserRequestDTO): User {
        if (userRepository.findByUsername(userRequestDTO.username) != null) {
            throw UserAlreadyExistsException()
        }

        return userRepository.save(convertToEntity(userRequestDTO))
    }

    private fun getCurrentUserId(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal

        if (principal is UserDetails) {
            return userRepository.findByUsername(principal.username)?.id ?: throw EntityNotFoundException()
        } else {
            throw IllegalStateException()
        }

    }

    private fun convertToEntity(requestDTO: MeasurementRequestDTO, userId: String): Measurement {
        return Measurement("", userId, LocalDateTime.now(), requestDTO.bpm, requestDTO.lower, requestDTO.upper)
    }

    private fun convertToEntity(requestDTO: UserRequestDTO): User {
        return User("", requestDTO.username, passwordEncoder.encode(requestDTO.password))
    }

}