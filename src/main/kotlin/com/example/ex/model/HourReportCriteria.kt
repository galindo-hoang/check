package com.example.ex.model

import java.util.*

data class HourReportCriteria(
    val levels: List<String>,
    val startMonth: Date,
    val endMonth:Date,
    val projectCodes: List<String>
)