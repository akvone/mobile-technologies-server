package com.akvone.mobiletechnologies.plain_objects

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Measurement(@Id val id: String, val bpm: Int)