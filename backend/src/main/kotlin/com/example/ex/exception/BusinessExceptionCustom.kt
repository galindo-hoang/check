package com.example.ex.exception

import org.springframework.http.HttpStatus

class BusinessExceptionCustom(override val message: String = "BusinessException") : Exception(message){
}