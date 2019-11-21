package com.akvone.mobiletechnologies.plain_object

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Measurement(@Id val id: String, val createdAt: LocalDateTime, val bpm: Int, val lower: Int, val upper: Int)