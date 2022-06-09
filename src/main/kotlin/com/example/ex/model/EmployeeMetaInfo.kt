package com.example.ex.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.opencsv.bean.CsvBindByName
import javax.persistence.*

@Entity
@Table(name = "Employ_Meta_Info")
class EmployeeMetaInfo: EntitySuper() {
    @Id
    @Column(name = "Visa")
    @CsvBindByName(column = "Visa")
    var visa: String = ""

    @Column(name = "Name")
    @CsvBindByName(column = "Name")
    var name: String = ""

    @Column(name = "First_Name")
    @CsvBindByName(column = "First name")
    var firstName: String = ""

    @Column(name = "Last_Name")
    @CsvBindByName(column = "Last Name")
    var lastName: String = ""

    @Column(name = "Division")
    @CsvBindByName(column = "Division")
    var division: String = ""

    @Column(name = "Is_Mgr")
    @CsvBindByName(column = "Is Mgr ? ")
    var ismgr: String = ""

    @Column(name = "Group_sa")
    @CsvBindByName(column = "Group")
    var groupsa: String = ""

    @Column(name = "Working_Hours")
    @CsvBindByName(column = "Working hours")
    var workingHours: Double = 0.0

    @Column(name = "Worked_Hours")
    @CsvBindByName(column = "Worked hours")
    var workedHours: Double = 0.0

    @Column(name = "Difference")
    @CsvBindByName(column = "Difference")
    var difference: Double = 0.0

    @Column(name = "Cumul_Diff")
    @CsvBindByName(column = "Cumul diff")
    var cumulDiff: Double = 0.0

    @Column(name = "Vac_Left")
    @CsvBindByName(column = "Vac left")
    var vecLeft: Double = 0.0

    @Column(name = "Entrance")
    @CsvBindByName(column = "Entrance")
    var entrance: String = ""

    @Column(name = "Resignation")
    @CsvBindByName(column = "Resignation")
    var resignation: Int? = null

    @Column(name = "CH_Prd")
    @CsvBindByName(column = "CH Prd")
    var chPrd: Double = 0.0

    @Column(name = "ch_Prd_Percent")
    @CsvBindByName(column = "CH Prd %")
    var chPrdPercent: Double = 0.0

    @Column(name = "Absence")
    @CsvBindByName(column = "Absence")
    var absence: Double = 0.0

    @Column(name = "Sort")
    @CsvBindByName(column = "Sort")
    var sort: Int = 0

    @Column(name = "Forfait")
    @CsvBindByName(column = "Forfait")
    var forfait: Boolean = false

    @Column(name = "Part_Time")
    @CsvBindByName(column = "Part-time")
    var partTime: Int = 0

    @Column(name = "Calculated")
    @CsvBindByName(column = "Calculated Part-time %")
    var calculated: Double = 0.0

    @Column(name = "Calculated_Vac_Left")
    @CsvBindByName(column = "Calculated Vac left (d)")
    var calculatedVecLeft: Double = 0.0

    @Column(name = "Calculated_Is_Mgr")
    @CsvBindByName(column = "Calculated Is Mgr ?")
    var calculatedIsMgr: String = ""

    @Column(name = "VN_Entry")
    @CsvBindByName(column = "VN Entry")
    var vnEntry: String = ""

    @Column(name = "Calculated_Division")
    @CsvBindByName(column = "Calculated Division")
    var calculatedDivision: String = ""

    @Column(name = "Calculated_Overview")
    @CsvBindByName(column = "Calculated Overview")
    var calculatedOverview: String = ""

    @Column(name = "Holidays")
    @CsvBindByName(column = "Holidays")
    var holidays: Int = 0

    @Column(name = "Illness")
    @CsvBindByName(column = "Illness")
    var illness: Int = 0

    @Column(name = "Trainings")
    @CsvBindByName(column = "Trainings")
    var trainings: Int = 0

    @Column(name = "Onboarding")
    @CsvBindByName(column = "Onboarding")
    var onboarding: Int = 0

    @Column(name = "Others")
    @CsvBindByName(column = "Others")
    var others: Int = 0

    @OneToOne(mappedBy = "metaInfo", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var capacity: Capacity? = null

//    @OneToOne(mappedBy = "employMetaInfo", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
//    lateinit var employeeHourReport: EmployeeHourReport

    @OneToMany( mappedBy = "supervisor", fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonManagedReference
    var supervisors: MutableSet<EmployeeRole> = mutableSetOf()

    @OneToMany(mappedBy = "abbreviation", fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonManagedReference
    var abbreviations: MutableSet<EmployeeRole> = mutableSetOf()

    @OneToMany(mappedBy = "metaInfo", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @OrderBy("id")
    @JsonManagedReference
    var employeeMonthly: MutableSet<EmployeeMonthly> = mutableSetOf()

}