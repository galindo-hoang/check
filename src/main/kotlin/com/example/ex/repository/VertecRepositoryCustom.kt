package com.example.ex.repository

import com.example.ex.dto.VertecDto

interface VertecRepositoryCustom {
    fun findAllVertecByMonthYear(data: HashMap<String, Any>): MutableList<VertecDto>
}