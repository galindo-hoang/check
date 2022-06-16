package com.example.ex.dto

import javax.persistence.Column
import javax.persistence.Id

class EmployeeHourReportDto {
    @Id
    @Column(name = "Visa")
    var visa: String? = null

    @Column(name = "Name")
    var name: String? = null

    @Column(name = "First_Name")
    var firstName: String? = null

    @Column(name = "Last_Name")
    var lastName: String? = null

    @Column(name = "Division")
    var division: String? = null

    @Column(name = "Is_Mgr")
    var ismgr: String? = null

    @Column(name = "Groups")
    var group: String? = null

    @Column(name = "Working_Hours")
    var workingHours: Double = 0.0

    @Column(name = "Worked_Hours")
    var workedHours: Double = 0.0

    @Column(name = "Difference")
    var difference: Double = 0.0

    @Column(name = "Cumul_Diff")
    var cumulDiff: Double = 0.0

    @Column(name = "Vac_Left")
    var vecLeft: Double = 0.0

    @Column(name = "Entrance")
    var entrance: String? = null

    @Column(name = "Resignation")
    var resignation: Int = 0

    @Column(name = "CH_Prd")
    var chPrd: Double = 0.0

    @Column(name = "CHPrd")
    var chPrdPercent: Double = 0.0

    @Column(name = "Absence")
    var absence: Double = 0.0

    @Column(name = "Sort")
    var sort: Int = 0

    @Column(name = "Forfait")
    var forfait: Boolean = false

    @Column(name = "Part_Time")
    var partTime: Int = 0

    @Column(name = "Calculated")
    var calculated: Double = 0.0

    @Column(name = "Calculated_Vac_Left")
    var calculatedVecLeft: Double = 0.0

    @Column(name = "Calculated_Is_Mgr")
    var calculatedIsMgr: String? = null

    @Column(name = "VN_Entry")
    var vnEntry: String? = null

    @Column(name = "Calculated_Division")
    var calculatedDivision: String? = null

    @Column(name = "Calculated_Overview")
    var calculatedOverview: String? = null

    @Column(name = "Holidays")
    var holidays: Int = 0

    @Column(name = "Illness")
    var illness: Int = 0

    @Column(name = "Trainings")
    var trainings: Int = 0

    @Column(name = "Onboaring")
    var onboarding: Int = 0

    @Column(name = "Others")
    var others: Int = 0

    @Column(name = "ConsolidatedHours")
    var consolidatedHours: Double = 0.0
}
