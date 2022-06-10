package com.example.ex.repository

import java.time.Month

interface VertecRepositoryCustom {
    fun findAllVertecByMonthYear(month: Int, year: Int)
}