package com.example.ex.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(value = [FileNotFoundExceptionCustom::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handlerNotFoundException(ex:FileNotFoundExceptionCustom): ErrorMessage {
        return ErrorMessage(HttpStatus.BAD_REQUEST, "${ ex.message }")
    }
}