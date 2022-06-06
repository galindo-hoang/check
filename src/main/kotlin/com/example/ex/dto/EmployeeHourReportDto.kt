package com.example.ex.dto

import javax.persistence.Column
import javax.persistence.Id

class EmployeeHourReportDto {
    @Id
    @Column(name = "Visa")
    var visa: String = ""

    @Column(name = "Name")
    var name: String = ""

    @Column(name = "First_Name")
    var firstName: String = ""

    @Column(name = "Last_Name")
    var lastName: String = ""

    @Column(name = "Division")
    var division: String = ""

    @Column(name = "Is_Mgr")
    var isMgr: String = ""

    @Column(name = "Groups")
    var group: String = ""

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
    var entrance: String

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
    var calculatedIsMgr: String

    @Column(name = "VN_Entry")
    var vnEntry: String

    @Column(name = "Calculated_Division")
    var calculatedDivision: String

    @Column(name = "Calculated_Overview")
    var calculatedOverview: String

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


    constructor(
        visa: String,
        name: String,
        firstName: String,
        lastName: String,
        division: String,
        isMgr: String,
        group: String,
        workingHours: Double,
        workedHours: Double,
        difference: Double,
        cumulDiff: Double,
        vecLeft: Double,
        entrance: String,
        resignation: Int,
        chPrd: Double,
        ChPrdPercent: Double,
        absence: Double,
        sort: Int,
        forfait: Boolean,
        partTime: Int,
        calculated: Double,
        calculatedVecLeft: Double,
        calculatedIsMgr: String,
        vnEntry: String,
        calculatedDivision: String,
        calculatedOverview: String,
        holidays: Int,
        illness: Int,
        trainings: Int,
        onBoarding: Int,
        others: Int,
        consolidatedHours: Double
    ) {
        this.visa = visa
        this.name = name
        this.firstName = firstName
        this.lastName = lastName
        this.division = division
        this.isMgr = isMgr
        this.group = group
        this.workingHours = workingHours
        this.workedHours = workedHours
        this.difference = difference
        this.cumulDiff = cumulDiff
        this.vecLeft = vecLeft
        this.entrance = entrance
        this.resignation = resignation
        this.chPrd = chPrd
        this.chPrdPercent = ChPrdPercent
        this.absence = absence
        this.sort = sort
        this.forfait = forfait
        this.partTime = partTime
        this.calculated = calculated
        this.calculatedVecLeft = calculatedVecLeft
        this.calculatedIsMgr = calculatedIsMgr
        this.vnEntry = vnEntry
        this.calculatedDivision = calculatedDivision
        this.calculatedOverview = calculatedOverview
        this.holidays = holidays
        this.illness = illness
        this.trainings = trainings
        this.onboarding = onBoarding
        this.others = others
        this.consolidatedHours = consolidatedHours
    }

    class Builder(
        private var visa: String = "",
        private var name: String = "",
        private var firstName: String = "",
        private var lastName: String = "",
        private var division: String = "",
        private var isMgr: String = "",
        private var group: String = "",
        private var workingHours: Double = 0.0,
        private var workedHours: Double = 0.0,
        private var difference: Double = 0.0,
        private var cumulDiff: Double = 0.0,
        private var vecLeft: Double = 0.0,
        private var entrance: String = "",
        private var resignation: Int? = null,
        private var chPrd: Double = 0.0,
        private var chPrdPercent: Double = 0.0,
        private var absence: Double = 0.0,
        private var sort: Int = 0,
        private var forfait: Boolean = false,
        private var partTime: Int = 0,
        private var calculated: Double = 0.0,
        private var calculatedVecLeft: Double = 0.0,
        private var calculatedIsMgr: String = "",
        private var vnEntry: String = "",
        private var calculatedDivision: String = "",
        private var calculatedOverview: String = "",
        private var holidays: Int = 0,
        private var illness: Int = 0,
        private var trainings: Int = 0,
        private var onboarding: Int = 0,
        private var others: Int = 0,
        private var consolidatedHours: Double = 0.0,
    ) {
        fun visa(visa: String) = apply { this.visa = visa }
        fun name(name: String) = apply { this.name = name }
        fun firstName(firstName: String) = apply { this.firstName = firstName }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun division(division: String) = apply { this.division = division }
        fun mgr(mgr: String) = apply { this.isMgr = mgr }
        fun group(group: String) = apply { this.group = group }
        fun workingHours(workingHours: Double) = apply { this.workingHours = workingHours }
        fun workedHours(workedHours: Double) = apply { this.workedHours = workedHours }
        fun difference(difference: Double) = apply { this.difference = difference }
        fun cumulDiff(cumulDiff: Double) = apply { this.cumulDiff = cumulDiff }
        fun vecLeft(vecLeft: Double) = apply { this.vecLeft = vecLeft }
        fun entrance(entrance: String) = apply { this.entrance = entrance }
        fun resignation(resignation: Int) = apply { this.resignation = resignation }
        fun chPrd(chProd: Double) = apply { this.chPrd = chProd }
        fun chPrdPercent(chPrdPercent: Double) = apply { this.chPrdPercent = chPrdPercent }
        fun absence(absence: Double) = apply { this.absence = absence }
        fun sort(sort: Int) = apply { this.sort = sort }
        fun forFait(forFait: Boolean) = apply { this.forfait = forFait }
        fun partTime(partTime: Int) = apply { this.partTime = partTime }
        fun calculated(calculated: Double) = apply { this.calculated = calculated }
        fun calculatedVecLeft(calculatedVecLeft: Double) = apply { this.calculatedVecLeft = calculatedVecLeft }
        fun calculatedIsMgr(calculatedIsMgr: String) = apply { this.calculatedIsMgr = calculatedIsMgr }
        fun vnEntry(vnEntry: String) = apply { this.vnEntry = vnEntry }
        fun calculatedDivision(calculatedDivision: String) = apply { this.calculatedDivision = calculatedDivision }
        fun calculatedOverview(calculatedOverview: String) = apply { this.calculatedOverview = calculatedOverview }
        fun holidays(holidays: Int) = apply { this.holidays = holidays }
        fun illness(illness: Int) = apply { this.illness = illness }
        fun trainings(trainings: Int) = apply { this.trainings = trainings }
        fun onBoarding(onBoarding: Int) = apply { this.onboarding = onBoarding }
        fun others(others: Int) = apply { this.others = others }
        fun consolidatedHours(consolidatedHours: Double) = apply { this.consolidatedHours = consolidatedHours }
        fun build() = EmployeeHourReportDto(
            visa,
            name,
            firstName,
            lastName,
            division,
            isMgr,
            group,
            workingHours,
            workedHours,
            difference,
            cumulDiff,
            vecLeft,
            entrance,
            resignation ?: 0,
            chPrd,
            chPrdPercent,
            absence,
            sort,
            forfait,
            partTime,
            calculated,
            calculatedVecLeft,
            calculatedIsMgr,
            vnEntry,
            calculatedDivision,
            calculatedOverview,
            holidays,
            illness,
            trainings,
            onboarding,
            others,
            consolidatedHours
        )

    }
}
