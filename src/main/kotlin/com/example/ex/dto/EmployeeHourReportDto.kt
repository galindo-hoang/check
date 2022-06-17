package com.example.ex.dto

import com.google.gson.annotations.SerializedName
import javax.persistence.Column
import javax.persistence.Id

class EmployeeHourReportDto {
    @SerializedName("Visa")
    var visa: String? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("First_Name")
    var firstName: String? = null

    @SerializedName("Last_Name")
    var lastName: String? = null

    @SerializedName("Division")
    var division: String? = null

    @SerializedName("Is_Mgr")
    var ismgr: String? = null

    @SerializedName("Groups")
    var group: String? = null

    @SerializedName("Working_Hours")
    var workingHours: Double = 0.0

    @SerializedName("Worked_Hours")
    var workedHours: Double = 0.0

    @SerializedName("Difference")
    var difference: Double = 0.0

    @SerializedName("Cumul_Diff")
    var cumulDiff: Double = 0.0

    @SerializedName("Vac_Left")
    var vecLeft: Double = 0.0

    @SerializedName("Entrance")
    var entrance: String? = null

    @SerializedName("Resignation")
    var resignation: Int = 0

    @SerializedName("CH_Prd")
    var chPrd: Double = 0.0

    @SerializedName("CHPrd")
    var chPrdPercent: Double = 0.0

    @SerializedName("Absence")
    var absence: Double = 0.0

    @SerializedName("Sort")
    var sort: Int = 0

    @SerializedName("Forfait")
    var forfait: Boolean = false

    @SerializedName("Part_Time")
    var partTime: Int = 0

    @SerializedName("Calculated")
    var calculated: Double = 0.0

    @SerializedName("Calculated_Vac_Left")
    var calculatedVecLeft: Double = 0.0

    @SerializedName("Calculated_Is_Mgr")
    var calculatedIsMgr: String? = null

    @SerializedName("VN_Entry")
    var vnEntry: String? = null

    @SerializedName("Calculated_Division")
    var calculatedDivision: String? = null

    @SerializedName("Calculated_Overview")
    var calculatedOverview: String? = null

    @SerializedName("Holidays")
    var holidays: Int = 0

    @SerializedName("Illness")
    var illness: Int = 0

    @SerializedName("Trainings")
    var trainings: Int = 0

    @SerializedName("Onboaring")
    var onboarding: Int = 0

    @SerializedName("Others")
    var others: Int = 0

    @SerializedName("ConsolidatedHours")
    var consolidatedHours: Double = 0.0
}
