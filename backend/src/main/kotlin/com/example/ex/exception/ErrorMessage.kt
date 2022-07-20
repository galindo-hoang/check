package com.example.ex.exception

import org.springframework.http.HttpStatus
import java.util.Date

data class ErrorMessage(
    val timestamp: Date,
    val message: String,
    val code: Int = 400,
    val error: HttpStatus = HttpStatus.BAD_REQUEST,
    val stackTrace: String = "",
)