package com.example.ex.dto

import com.example.ex.utils.Constant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VertecDto {
    @SerialName(Constant.MONTH_YEAR)
    var monthYear: String? = null

    @SerialName(Constant.VISA)
    var visaVertec: String? = null

    @SerialName(Constant.SUB_PROJECT)
    var subproject: String? = null

//    constructor(monthYear: String?, visa: EmployeeMetaInfo, subproject: String?) : this() {
//        this.monthYear = monthYear
//        this.visa = visa
//        this.subproject = subproject
//    }

    //    @SerialName("MonthFilter") val monthFilter: Int? = null
//    @SerialName("GRP-PRJ") val grpPrj: String? = null
//    @SerialName("P") val p: String? = null
//    @Transient
//    @SerialName("Date")
//    val formatDate: String = "2022-1-1"
//
//    @Contextual
//    val date: Date = Date.valueOf(formatDate)
//    @SerialName("Code") val code: String? = null
//    @SerialName("Hrs") val hrs: Double? = null
//    @SerialName("Comment") val comment: String? = null
//    @SerialName("Description") val description: String = ""
//    @SerialName("VN ?") val isVn: Boolean? = null
//    @SerialName("Subproject name") val subprojectName: String? = null
//    @SerialName("Project") val project: Int? = null
//    @SerialName("Project name") val projectName: String? = null
//    @SerialName("VN Hrs") val vnHrs: Double? = null
//    @SerialName("CH Hrs") val chHrs: Double? = null
//    @SerialName("Unique") val unique: Int? = null
//    @SerialName("Calculated subproject name") val calculatedSubprojectName: String? = null
//    @SerialName("Position") val position: String? = null


}