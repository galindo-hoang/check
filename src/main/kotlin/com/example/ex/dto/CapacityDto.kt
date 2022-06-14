package com.example.ex.dto

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
class CapacityDto {
    @SerialName("Visa")
    lateinit var visa: String

    @SerialName("Department")
    var department: String = ""

    @SerialName("StartDate")
    var startDate: String = ""

    @SerialName("EndDate")
    var endDate: String = ""

    override fun toString(): String {
        return "CapacityDto(visa='$visa', department='$department', startDate='$startDate', endDate='$endDate')"
    }


}