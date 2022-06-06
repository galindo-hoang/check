package com.example.ex.dto

import java.sql.Date

data class HourReportCriteriaDto(
    val levels: List<String>,
    val startMonth: Date,
    val endMonth:Date,
    val projectCodes: List<String>
)