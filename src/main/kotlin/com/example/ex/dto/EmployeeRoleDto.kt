package com.example.ex.dto

import com.google.gson.annotations.SerializedName

class EmployeeRoleDto {
    @SerializedName("Employee Nr")
    var employeeNr:Int? = null

    @SerializedName("Active")
    var active: String? = null

    @SerializedName("Last")
    var last: String = ""

    @SerializedName("First")
    var first:String = ""

    @SerializedName("Contract")
    var contract:Char? = null

    @SerializedName("Forfait")
    var forfait:Char? = null

    @SerializedName("User Level")
    var userLevel:String? = null

    @SerializedName("Level")
    var level:Int = 0

    @SerializedName("Sub-level")
    var subLevel:Int = 0

    @SerializedName("Part-time")
    var partTime:Double? = null

    @SerializedName("Supervisor")
    var supervisorss:String? = null

    @SerializedName("Abbreviation")
    var abbreviationss:String? = null
}