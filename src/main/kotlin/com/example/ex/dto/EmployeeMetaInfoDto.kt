package com.example.ex.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class EmployeeMetaInfoDto{
    @SerializedName("Visa")
    var visa: String = ""

    @SerializedName("Name")
    var name: String = ""

    @SerializedName("First name")
    var firstName: String? = null

    @SerializedName("Last Name")
    var lastName: String? = null

    @SerializedName("Division")
    var division: String? = null

    @SerializedName("Is Mgr ? ")
    var ismgr: String? = null

    @SerializedName("Group")
    var groupsa: String? = null

    @SerializedName("Working hours")
    var workingHours: Double = 0.0

    @SerializedName("Worked hours")
    var workedHours: Double = 0.0

    @SerializedName("Difference")
    var difference: Double = 0.0

    @SerializedName("Cumul diff")
    var cumulDiff: Double = 0.0

    @SerializedName("Vac left")
    var vecLeft: Double = 0.0

    @SerializedName("Entrance")
    var entrance: String? = null

    @SerializedName("Resignation")
    var resignation: Int? = null

    @SerializedName("CH Prd")
    var chPrd: Double = 0.0

    @SerializedName("CH Prd %")
    var chPrdPercent: Double = 0.0

    @SerializedName("Absence")
    var absence: Double = 0.0

    @SerializedName("Sort")
    var sort: Int = 0

    @SerializedName("Forfait")
    var forfait: Boolean = false

    @SerializedName("Part-time")
    var partTime: Int = 0

    @SerializedName("Calculated Part-time %")
    var calculated: Double = 0.0

    @SerializedName("Calculated Vac left (d)")
    var calculatedVecLeft: Double = 0.0

    @SerializedName("Calculated Is Mgr ?")
    var calculatedIsMgr: String? = null

    @SerializedName("VN Entry")
    var vnEntry: String? = null

    @SerializedName("Calculated Division")
    var calculatedDivision: String? = null

    @SerializedName("Calculated Overview")
    var calculatedOverview: String? = null

    @SerializedName("Holidays")
    var holidays: Int = 0

    @SerializedName("Illness")
    var illness: Int = 0

    @SerializedName("Trainings")
    var trainings: Int = 0

    @SerializedName("Onboarding")
    var onboarding: Int = 0

    @SerializedName("Others")
    var others: Int = 0
}