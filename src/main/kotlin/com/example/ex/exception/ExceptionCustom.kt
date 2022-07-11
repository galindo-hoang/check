package com.example.ex.exception

import org.springframework.http.HttpStatus

class ExceptionCustom(message: String = "Error", val status: HttpStatus) : RuntimeException(message){
}