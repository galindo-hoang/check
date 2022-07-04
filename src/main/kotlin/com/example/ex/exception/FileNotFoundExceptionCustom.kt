package com.example.ex.exception

import org.springframework.http.HttpStatus

class FileNotFoundExceptionCustom(message: String,val status: HttpStatus) : RuntimeException(message) {
}