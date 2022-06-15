package com.example.ex.dto

import com.google.gson.annotations.SerializedName
import java.util.*

class EmployeeMonthlyDto {

    @SerializedName("Visa")
    var visa: String = ""

    @SerializedName("Date")
    var dateJava: Date? = null

    @SerializedName("Code")
    var code:String = ""

    @SerializedName("Hrs")
    var hours: Double = 0.0

    @SerializedName("Comment")
    var comment:String = ""

    @SerializedName("Description")
    var description:String = ""

    @SerializedName("VN ?")
    var vn: Boolean = false

    @SerializedName("Subproject")
    var subProject:String = ""

    @SerializedName("Subproject name")
    var subprojectName:String = ""

    @SerializedName("Project")
    var project:String = ""

    @SerializedName("Project name")
    var projectName:String = ""

    @SerializedName("VN Hrs")
    var vnHrs:Double = 0.0

    @SerializedName("CH Hrs")
    var chHrs:Double = 0.0

    @SerializedName("Unique")
    var unique:Int = 0

    @SerializedName("Calculated subproject name")
    var calculatedSubprojectName:String = ""

    @SerializedName("Division")
    var division:String = ""
    override fun toString(): String {
        return "EmployeeMonthlyDto(visa='$visa', dateString='$dateJava', code='$code', hours=$hours, comment='$comment', description='$description', vn=$vn, subProject='$subProject', subprojectName='$subprojectName', project=$project, projectName='$projectName', vnHrs=$vnHrs, chHrs=$chHrs, unique=$unique, calculatedSubprojectName='$calculatedSubprojectName', division='$division')"
    }


}