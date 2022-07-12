package com.example.ex.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
class CustomExceptionHandler {
    @ExceptionHandler(value = [TechExceptionCustom::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handlerNotFoundException(ex:TechExceptionCustom): ErrorMessage {

        return ErrorMessage(Date(), ex.e.message.toString(), stackTrace = ex.e.stackTraceToString())
    }
    @ExceptionHandler(value = [BusinessExceptionCustom::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handlerNotFoundException(ex:BusinessExceptionCustom): ErrorMessage {
        return ErrorMessage(Date(),ex.message)
    }
}