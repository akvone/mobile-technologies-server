package com.akvone.mobiletechnologies.repositorie

import com.akvone.mobiletechnologies.plain_object.Measurement
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MeasurementRepository : MongoRepository<Measurement, String> {
    fun findByIdAndUserId(id: String, userId: String): Measurement?
    fun findByUserId(userId: String): List<Measurement>
}