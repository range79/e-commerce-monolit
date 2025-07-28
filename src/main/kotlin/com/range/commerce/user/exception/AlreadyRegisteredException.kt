package com.range.commerce.user.exception

import org.springframework.http.HttpStatus

class AlreadyRegisteredException(msg: String): AbstractExceptionHandler(msg ,HttpStatus.CONFLICT)