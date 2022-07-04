package com.example.ex.model

import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "Employee_Meta_Info")
class EmployeeMetaInfo: EntitySuper() {
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

    @Column(name = "Group_sa")
    var groupsa: String? = null

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
    var entrance: Date? = null

    @Column(name = "Resignation")
    var resignation: Int? = null

    @Column(name = "CH_Prd")
    var chPrd: Double = 0.0

    @Column(name = "ch_Prd_Percent")
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
    var calculated: String? = ""

    @Column(name = "Calculated_Vac_Left")
    var calculatedVecLeft: Double = 0.0

    @Column(name = "Calculated_Is_Mgr")
    var calculatedIsMgr: String? = null

    @Column(name = "VN_Entry")
    var vnEntry: Date? = null

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

    @Column(name = "Onboarding")
    var onboarding: Int = 0

    @Column(name = "Others")
    var others: Int = 0

    @OneToMany(mappedBy = "visa", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OrderBy("id")
    var capacity: MutableSet<Capacity> = mutableSetOf()

    @OneToMany( mappedBy = "supervisor", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OrderBy("id")
    var supervisors: MutableSet<EmployeeRole> = mutableSetOf()

    @OneToMany(mappedBy = "abbreviation", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OrderBy("id")
    var abbreviations: MutableSet<EmployeeRole> = mutableSetOf()

    @OneToMany(mappedBy = "metaInfo", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OrderBy("id")
    var employeeMonthly: MutableSet<EmployeeMonthly> = mutableSetOf()

}