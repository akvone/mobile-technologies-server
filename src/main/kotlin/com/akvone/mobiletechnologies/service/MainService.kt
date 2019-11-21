package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.exception.NotFoundException
import com.akvone.mobiletechnologies.plain_object.Measurement
import com.akvone.mobiletechnologies.plain_object.MeasurementRequestDTO
import com.akvone.mobiletechnologies.plain_object.User
import com.akvone.mobiletechnologies.plain_object.UserRequestDTO
import com.akvone.mobiletechnologies.repositorie.MeasurementRepository
import com.akvone.mobiletechnologies.repositorie.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import java.time.LocalDateTime

@Service
class MainService(val measurementRepository: MeasurementRepository,
                  val userRepository: UserRepository,
                  val passwordEncoder: PasswordEncoder) {

    fun createMeasurement(requestDTO: MeasurementRequestDTO) = measurementRepository.save(convertToEntity(requestDTO))
    fun getMeasurement(id: String): Measurement = measurementRepository.findById(id).orElseThrow(::NotFoundException)
    fun getMeasurements(): List<Measurement> = measurementRepository.findAll()
    fun deleteMeasurements() = measurementRepository.deleteAll()

    fun createUser(userRequestDTO: UserRequestDTO): User {
        if (userRepository.findByUsername(userRequestDTO.username) != null) {
            throw HttpClientErrorException(HttpStatus.CONFLICT)
        }

        return userRepository.save(convertToEntity(userRequestDTO))
    }

    private fun convertToEntity(requestDTO: MeasurementRequestDTO): Measurement {
        return Measurement("", LocalDateTime.now(), requestDTO.bpm, requestDTO.lower, requestDTO.upper)
    }

    private fun convertToEntity(requestDTO: UserRequestDTO): User {
        return User("", requestDTO.username, passwordEncoder.encode(requestDTO.password))
    }

}