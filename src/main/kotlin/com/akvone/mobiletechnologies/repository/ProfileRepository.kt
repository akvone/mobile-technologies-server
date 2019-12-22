package com.akvone.mobiletechnologies.repository

import com.akvone.mobiletechnologies.plain_object.Profile
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : MongoRepository<Profile, String> {
    fun findByUserId(currentUserId: String): Profile
}
