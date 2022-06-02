package com.example.ex.model

import java.util.Date
import javax.persistence.*

@Entity
@Table
class EmployeeHourReport{
    @Id
    @Column(name = "Visa")
    var id:Long = 0

    @OneToOne(cascade = [CascadeType.ALL])
    @MapsId
    @JoinColumn(name = "Visa")
    var employMetaInfo:EmployMetaInfo? = null

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
    var group:String = ""

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
    lateinit var entrance:String

    @Column(name = "Resignation")
    var resignation:Int = 0

    @Column(name = "CH_Prd")
    var chPrd:Double = 0.0

    @Column(name = "CHPrd")
    var CHPrd:Double = 0.0

    @Column(name = "Absence")
    var absence:Double = 0.0

    @Column(name = "Sort")
    var sort: Int = 0

    @Column(name = "Forfait")
    var forfait:Boolean = false

    @Column(name = "Part_Time")
    var partTime: Int = 0

    @Column(name = "Calculated")
    var calculated:Double = 0.0

    @Column(name = "Calculated_Vac_Left")
    var calculatedVecLeft:Double = 0.0

    @Column(name = "Calculated_Is_Mgr")
    lateinit var calculatedIsMgr:String

    @Column(name = "VN_Entry")
    lateinit var vnEntry:Date

    @Column(name = "Calculated_Division")
    lateinit var calculatedDivision:String

    @Column(name = "Calculated_Overview")
    lateinit var calculatedOverview:String

    @Column(name = "Holidays")
    var holidays:Int = 0

    @Column(name = "Illness")
    var illness:Int = 0

    @Column(name = "Trainings")
    var trainings:Int = 0

    @Column(name = "Onboaring")
    var onboarding:Int = 0

    @Column(name = "Others")
    var others:Int = 0

    @Column(name = "ConsolidatedHours")
    var consolidatedHours:Double = 0.0
}
