package com.example.ex.dto

import java.io.Serializable

class EmployeeMetaInfoDto : Serializable {
    var visa: String = ""
    var name: String = ""
    var firstName: String? = null
    var lastName: String? = null
    var division: String? = null
    var isMgr: String? = null
    var groupsa: String? = null
    var workingHours: Double = 0.0
    var workedHours: Double = 0.0
    var difference: Double = 0.0
    var cumulDiff: Double = 0.0
    var vecLeft: Double = 0.0
    var entrance: String? = null
    var resignation: Int? = null
    var chPrd: Double = 0.0
    var chPrdPercent: Double = 0.0
    var absence: Double = 0.0
    var sort: Int = 0
    var forfait: Boolean = false
    var partTime: Int = 0
    var calculated: Double = 0.0
    var calculatedVecLeft: Double = 0.0
    var calculatedIsMgr: String? = null
    var vnEntry: String? = null
    var calculatedDivision: String? = null
    var calculatedOverview: String? = null
    var holidays: Int = 0
    var illness: Int = 0
    var trainings: Int = 0
    var onboarding: Int = 0
    var others: Int = 0
}