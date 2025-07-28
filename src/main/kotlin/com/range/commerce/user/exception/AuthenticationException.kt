package com.range.commerce.user.exception

import org.springframework.http.HttpStatus

class AuthenticationException(msg: String): AbstractExceptionHandler(msg, HttpStatus.NOT_FOUND) {
}