package com.range.commerce.user.exception

import org.springframework.http.HttpStatus

abstract class AbstractExceptionHandler(msg: String,val status: HttpStatus): RuntimeException(msg) {
}