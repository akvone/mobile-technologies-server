package com.akvone.mobiletechnologies.service

import com.akvone.mobiletechnologies.exception.EntityNotFoundException
import com.akvone.mobiletechnologies.exception.UserAlreadyExistsException
import com.akvone.mobiletechnologies.plain_object.*
import com.akvone.mobiletechnologies.repository.MeasurementRepository
import com.akvone.mobiletechnologies.repository.ProfileRepository
import com.akvone.mobiletechnologies.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MainService(val measurementRepository: MeasurementRepository,
                  val userRepository: UserRepository,
                  val profileRepository: ProfileRepository,
                  val passwordEncoder: PasswordEncoder) {

    fun createMeasurement(requestDTO: MeasurementRequestDTO) {
        val measurement = buildMeasurement(requestDTO, getCurrentUserId())

        measurementRepository.save(measurement)
    }

    fun getMeasurement(id: String): MeasurementDTO {
        val measurement = measurementRepository.findByIdAndUserId(id, getCurrentUserId())

        return measurement?.convertToDto() ?: throw EntityNotFoundException()
    }

    fun getLastMeasurement(): MeasurementDTO {
        val measurement = measurementRepository.findFirstByUserIdOrderByCreatedAtDesc(getCurrentUserId())

        return measurement?.convertToDto() ?: throw EntityNotFoundException()
    }

    fun getMeasurements(): List<MeasurementDTO> {
        val userId = getCurrentUserId()

        val measurements = measurementRepository.findByUserId(userId)
        return measurements.map { it.convertToDto() }
    }

    fun createUser(userRequestDTO: UserRequestDTO) {
        if (userRepository.findByUsername(userRequestDTO.username) != null) {
            throw UserAlreadyExistsException()
        }

        val user = userRepository.insert(buildUser(userRequestDTO))
        // TODO:Find a way to get user id right after insertion
        //      Now MongoTemplate#populateIdIfNecessary won't work because it updates only null values.
        val userId = userRepository.findByUsername(user.username)!!.id

        profileRepository.save(buildProfile(userId, user))
    }

    fun updateProfile(profileDTO: ProfileDTO) {
        val profile = profileRepository.findByUserId(getCurrentUserId())

        with(profileDTO) {
            profile.name = name
            profile.age = age
            profile.height = height
            profile.weight = weight
        }

        profileRepository.save(profile)
    }

    fun getProfile(): ProfileDTO {
        val profile = profileRepository.findByUserId(getCurrentUserId())
        return profile.convertToDto()
    }

    private fun getCurrentUserId(): String {
        val principal = SecurityContextHolder.getContext().authentication.principal

        if (principal is UserDetails) {
            return userRepository.findByUsername(principal.username)?.id ?: throw EntityNotFoundException()
        } else {
            throw IllegalStateException()
        }

    }

    private fun buildMeasurement(requestDTO: MeasurementRequestDTO, userId: String): Measurement =
            with(requestDTO) { Measurement("", userId, LocalDateTime.now(), bpm, lower, upper) }

    private fun buildUser(requestDTO: UserRequestDTO) =
            User("", requestDTO.username, passwordEncoder.encode(requestDTO.password))

    private fun buildProfile(userId: String, user: User) = Profile("", userId, user.username)

}
