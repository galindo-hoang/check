package com.example.ex.dto

import com.google.gson.annotations.SerializedName
import java.util.Date


class CapacityDto {
    @SerializedName("Visa")
    var visa: String = ""

    @SerializedName("Department")
    var department: String = ""

    @SerializedName("StartDate")
    var startDate: Date? = null

    @SerializedName("EndDate")
    var endDate: Date? = null
    override fun toString(): String {
        return "CapacityDto(visa='$visa', department='$department', startDate='$startDate', endDate='$endDate')"
    }


}