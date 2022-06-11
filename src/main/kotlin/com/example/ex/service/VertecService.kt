package com.example.ex.service

import com.example.ex.dto.VertecDto

interface VertecService {
    fun loadVertecByMonthYear(month: Int, year: Int): MutableList<VertecDto>
    fun loadVertecIntoDB(month: Int, year: Int)
}