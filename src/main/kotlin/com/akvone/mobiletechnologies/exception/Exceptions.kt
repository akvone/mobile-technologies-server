package com.akvone.mobiletechnologies.exception

import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = NOT_FOUND)
class EntityNotFoundException : IllegalArgumentException()

@ResponseStatus(value = CONFLICT)
class UserAlreadyExistsException : IllegalStateException()