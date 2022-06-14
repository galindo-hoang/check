package com.example.ex.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class EmployeeMonthlyDto {

    @SerialName("Visa")
    var visa: String = ""

    @SerialName("Date")
    var dateString: String = ""

    @SerialName("Code")
    var code:String = ""

    @SerialName("Hrs")
    var hours: Double = 0.0

    @SerialName("Comment")
    var comment:String = ""

    @SerialName("Description")
    var description:String = ""

    @SerialName("VN ?")
    var vn: Boolean = false

    @SerialName("Subproject")
    var subProject:String = ""

    @SerialName("Subproject name")
    var subprojectName:String = ""

    @SerialName("Project")
    var project:Int = 0

    @SerialName("Project name")
    var projectName:String = ""

    @SerialName("VN Hrs")
    var vnHrs:Double = 0.0

    @SerialName("CH Hrs")
    var chHrs:Double = 0.0

    @SerialName("Unique")
    var unique:Int = 0

    @SerialName("Calculated subproject name")
    var calculatedSubprojectName:String = ""

    @SerialName("Division")
    var division:String = ""
    override fun toString(): String {
        return "EmployeeMonthlyDto(visa='$visa', dateString='$dateString', code='$code', hours=$hours, comment='$comment', description='$description', vn=$vn, subProject='$subProject', subprojectName='$subprojectName', project=$project, projectName='$projectName', vnHrs=$vnHrs, chHrs=$chHrs, unique=$unique, calculatedSubprojectName='$calculatedSubprojectName', division='$division')"
    }


}