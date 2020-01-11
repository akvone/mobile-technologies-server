package com.akvone.mobiletechnologies.repository

import com.akvone.mobiletechnologies.plain_object.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByUsername(username: String): User?
}