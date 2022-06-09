package com.example.ex.model

import kotlinx.serialization.SerialName
import java.sql.Date

class Vertec: EntitySuper() {
    @SerialName("Month") val month: String = ""
    @SerialName("MonthFilter") val monthFilter: Int = 0
    @SerialName("GRP-PRJ") val grpPrj: String = ""
    @SerialName("P") val p: String = ""
    @SerialName("Visa") val visa: String = ""
    @SerialName("Date") val date: Date? = null
    @SerialName("Code") val code: String = ""
    @SerialName("Hrs") val hrs: Double = 0.0
    @SerialName("Comment") val comment: String = ""
    @SerialName("Description") val description: String = ""
    @SerialName("VN ?") val isVn: Boolean = false
    @SerialName("Subproject") val subproject: String = ""
    @SerialName("Subproject name") val subprojectName: String = ""
    @SerialName("Project") val project: Int = 0
    @SerialName("Project name") val projectName: String = ""
    @SerialName("VN Hrs") val vnHrs: Double = 0.0
    @SerialName("CH Hrs") val chHrs: Int = 0
    @SerialName("Unique") val unique: Int = 0
    @SerialName("Calculated subproject name") val calculatedSubprojectName: String = ""
    @SerialName("Position") val position: String = ""
}