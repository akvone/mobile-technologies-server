package com.akvone.mobiletechnologies.plain_object

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class User(@Id val id: String, val username: String, val password: String)

@Document
data class Measurement(@Id val id: String, val userId: String, val createdAt: LocalDateTime,
                       val bpm: Int, val lower: Int, val upper: Int)

@Document
data class Profile(@Id val id: String, val userId: String,
                   var name: String, var age: Int? = null, var height: Int? = null, var weight: Double? = null)