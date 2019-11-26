package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.exception.EntityNotFoundException
import com.akvone.mobiletechnologies.exception.UserAlreadyExistsException
import com.akvone.mobiletechnologies.plain_object.*
import com.akvone.mobiletechnologies.repository.MeasurementRepository
import com.akvone.mobiletechnologies.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MainService(val measurementRepository: MeasurementRepository,
                  val userRepository: UserRepository,
                  val passwordEncoder: PasswordEncoder) {

    fun createMeasurement(requestDTO: MeasurementRequestDTO) {
        val userId = getCurrentUserId()
        val convertToEntity = convertToEntity(requestDTO, userId)

        measurementRepository.save(convertToEntity)
    }

    fun getMeasurement(id: String): MeasurementDTO {
        val userId = getCurrentUserId()

        val measurement = measurementRepository.findByIdAndUserId(id, userId) ?: throw EntityNotFoundException()
        return convertToDto(measurement)
    }

    fun getLastMeasurement(): MeasurementDTO {
        val measurement = measurementRepository.findFirstByUserIdOrderByCreatedAtDesc(getCurrentUserId())

        return measurement?.let { convertToDto(measurement) } ?: throw EntityNotFoundException()
    }

    fun getMeasurements(): List<MeasurementDTO> {
        val userId = getCurrentUserId()

        val measurements = measurementRepository.findByUserId(userId)
        return measurements.map { convertToDto(it) }
    }

    fun createUser(userRequestDTO: UserRequestDTO) {
        if (userRepository.findByUsername(userRequestDTO.username) != null) {
            throw UserAlreadyExistsException()
        }

        userRepository.save(convertToEntity(userRequestDTO))
    }

    private fun getCurrentUserId(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal

        if (principal is UserDetails) {
            return userRepository.findByUsername(principal.username)?.id ?: throw EntityNotFoundException()
        } else {
            throw IllegalStateException()
        }

    }

    private fun convertToDto(measurement: Measurement): MeasurementDTO {
        return with(measurement) { MeasurementDTO(id, createdAt, bpm, lower, upper) }
    }

    private fun convertToEntity(requestDTO: MeasurementRequestDTO, userId: String): Measurement {
        return Measurement("", userId, LocalDateTime.now(), requestDTO.bpm, requestDTO.lower, requestDTO.upper)
    }

    private fun convertToEntity(requestDTO: UserRequestDTO): User {
        return User("", requestDTO.username, passwordEncoder.encode(requestDTO.password))
    }

}