package com.example.ex.repository

import com.example.ex.dto.VertecDto
import java.time.Month

interface VertecRepositoryCustom {
    fun findAllVertecByMonthYear(month: Int, year: Int): MutableList<VertecDto>
}