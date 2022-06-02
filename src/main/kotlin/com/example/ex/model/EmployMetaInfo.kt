package com.example.ex.model

import com.opencsv.bean.CsvBindByName
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.PrimaryKeyJoinColumn
import javax.persistence.Table

@Entity
@Table(name = "EmployMetaInfo")
class EmployMetaInfo (
    @Id
    @Column(name = "Visa")
    @CsvBindByName(column = "Visa")
    var visa:String = "",

    @Column(name = "Name")
    @CsvBindByName(column = "Name")
    var name: String = "",

    @Column(name = "First_Name")
    @CsvBindByName(column = "First name")
    private var firstName: String = "",

    @Column(name = "Last_Name")
    @CsvBindByName(column = "Last Name")
    private var lastName: String = "",

    @Column(name = "Division")
    @CsvBindByName(column = "Division")
    private var division: String = "",

    @Column(name = "Is_Mgr")
    @CsvBindByName(column = "Is Mgr ? ")
    private var isMgr: String = "",

    @Column(name = "Group")
    @CsvBindByName(column = "Group")
    private var group:String = "",

    @Column(name = "Working_Hours")
    @CsvBindByName(column = "Working hours")
    private var workingHours: Double = 0.0,

    @Column(name = "Worked_Hours")
    @CsvBindByName(column = "Worked hours")
    private var workedHours: Double = 0.0,

    @Column(name = "Difference")
    @CsvBindByName(column = "Difference")
    private var difference: Double = 0.0,

    @Column(name = "Cumul_Diff")
    @CsvBindByName(column = "Cumul diff")
    private var cumulDiff: Double = 0.0,

    @Column(name = "Vac_Left")
    @CsvBindByName(column = "Vac left")
    private var vecLeft: Double = 0.0,

    @Column(name = "Entrance")
    @CsvBindByName(column = "Entrance")
    private var entrance: String = "",

    @Column(name = "Resignation")
    @CsvBindByName(column = "Resignation")
    private var resignation:Int = 0,

    @Column(name = "CH_Prd")
    @CsvBindByName(column = "CH Prd")
    private var chPrd:Double = 0.0,

    @Column(name = "CHPrd")
    @CsvBindByName(column = "CH Prd %")
    private var CHPrd:Double = 0.0,

    @Column(name = "Absence")
    @CsvBindByName(column = "Absence")
    private var absence:Double = 0.0,

    @Column(name = "Sort")
    @CsvBindByName(column = "Sort")
    private var sort: Int = 0,

    @Column(name = "Forfait")
    @CsvBindByName(column = "Forfait")
    private var forfait:Boolean = false,

    @Column(name = "Part_Time")
    @CsvBindByName(column = "Part-time")
    private var partTime: Int = 0,

    @Column(name = "Calculated")
    @CsvBindByName(column = "Calculated Part-time %")
    private var calculated:Double = 0.0,

    @Column(name = "Calculated_Vac_Left")
    @CsvBindByName(column = "Calculated Vac left (d)")
    private var calculatedVecLeft:Double = 0.0,

    @Column(name = "Calculated_Is_Mgr")
    @CsvBindByName(column = "Calculated Is Mgr ?")
    private var calculatedIsMgr:String = "",

    @Column(name = "VN_Entry")
    @CsvBindByName(column = "VN Entry")
    private var vnEntry: String = "",

    @Column(name = "Calculated_Division")
    @CsvBindByName(column = "Calculated Division")
    private var calculatedDivision:String = "",

    @Column(name = "Calculated_Overview")
    @CsvBindByName(column = "Calculated Overview")
    private var calculatedOverview:String = "",

    @Column(name = "Holidays")
    @CsvBindByName(column = "Holidays")
    private var holidays:Int = 0,

    @Column(name = "Illness")
    @CsvBindByName(column = "Illness")
    private var illness:Int = 0,

    @Column(name = "Trainings")
    @CsvBindByName(column = "Trainings")
    private var trainings:Int = 0,

    @Column(name = "Onboaring")
    @CsvBindByName(column = "Onboarding")
    private var onboarding:Int = 0,

    @Column(name = "Others")
    @CsvBindByName(column = "Others")
    private var others:Int = 0,

    @OneToOne(mappedBy = "metaInfo", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    private val capacity:Capacity? = null,

    @OneToOne(mappedBy = "metaInfo", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    private val employeeMonthly:EmployeeMonthly? = null,

    @OneToOne(mappedBy = "employMetaInfo", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    private val employeeHourReport:EmployeeHourReport? = null,
)