package com.akvone.mobiletechnologies.repositorie

import com.akvone.mobiletechnologies.plain_object.Measurement
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface MeasurementRepository : MongoRepository<Measurement, String>