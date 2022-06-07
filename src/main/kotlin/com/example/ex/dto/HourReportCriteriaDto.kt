package com.example.ex.dto

import java.sql.Date

data class HourReportCriteriaDto(
    var levels: MutableList<String>,
    val startMonth: Date,
    val endMonth:Date,
    val projectCodes: MutableList<String>
)