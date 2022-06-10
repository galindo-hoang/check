package com.example.ex.dto

import java.sql.Date

class EmployeeMonthlyDto {
    var visa: String = ""
    var date: Date? = null
    var code:String = ""
    var hours: Double = 0.0
    var comment:String = ""
    var description:String = ""
    var vn: Boolean = false
    var subProject:String = ""
    var subprojectName:String = ""
    var project:Long = 0
    var projectName:String = ""
    var vnHrs:Int = 0
    var chHrs:Int = 0
    var unique:Int = 0
    var calculatedSubprojectName:String = ""
    var division:String = ""

    constructor(
        visa: String,
        date: Date?,
        code: String,
        hours: Double,
        comment: String,
        description: String,
        vn: Boolean,
        subProject: String,
        subprojectName: String,
        project: Long,
        projectName: String,
        vnHrs: Int,
        chHrs: Int,
        unique: Int,
        calculatedSubprojectName: String,
        division: String
    ) {
        this.visa = visa
        this.date = date
        this.code = code
        this.hours = hours
        this.comment = comment
        this.description = description
        this.vn = vn
        this.subProject = subProject
        this.subprojectName = subprojectName
        this.project = project
        this.projectName = projectName
        this.vnHrs = vnHrs
        this.chHrs = chHrs
        this.unique = unique
        this.calculatedSubprojectName = calculatedSubprojectName
        this.division = division
    }
}