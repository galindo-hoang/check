package com.example.ex.dto

import java.sql.Date

class HourReportCriteriaDto{

    var levels: List<String> = listOf()
    lateinit var startMonth: Date
    lateinit var endMonth:Date
    var projectCodes: List<String> = listOf()
}