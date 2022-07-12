package com.example.ex.exception

class TechExceptionCustom(message: String = "TechException", val e: Exception) : RuntimeException(message){
}