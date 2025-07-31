package com.range.commerce.category.exception

import com.range.commerce.user.exception.AbstractExceptionHandler
import org.springframework.http.HttpStatus

class CategoryNotFoundException(msg: String): AbstractExceptionHandler(msg, HttpStatus.NOT_FOUND)