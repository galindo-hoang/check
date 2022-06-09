package com.example.ex.dto

import java.sql.Date

class HourReportCriteriaDto{

    var levels: List<String> = listOf()
    var startMonth: Date = Date.valueOf("2022-1-1")
    var endMonth:Date = Date.valueOf("2023-1-1")
    var projectCodes: List<String> = listOf()
}