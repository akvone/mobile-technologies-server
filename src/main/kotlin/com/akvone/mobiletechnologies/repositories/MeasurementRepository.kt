package com.akvone.mobiletechnologies.repositories

import com.akvone.mobiletechnologies.plain_objects.Measurement
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MeasurementRepository : MongoRepository<Measurement, String>