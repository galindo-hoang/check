package com.example.ex.repository

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.dto.WhoDoWhat
import com.example.ex.model.EmployeeMonthly
import java.sql.Date

interface EmployeeMonthlyRepositoryCustom {
    fun findByProjectGroup(projectGroup: String?): List<EmployeeMonthly>
    fun findHoursByMonthYearGroupByVisaProjectGroup(month: Int, year: Int): List<WhoDoWhat>
    fun readingLabelRowAndCol(): Pair<HashMap<String,Int>?,HashMap<String,Int>?>
    fun fillDataIntoXLSX(month: Int, year: Int, labels: Pair<HashMap<String, Int>, HashMap<String, Int>>, data: List<WhoDoWhat>): Boolean
    fun findEmployeeByMonth(month: Date): List<EmployeeMonthly>
    fun deleteEmployeeByMonth(month: Int)
}