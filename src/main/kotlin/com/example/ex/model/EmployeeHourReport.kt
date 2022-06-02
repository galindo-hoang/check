package com.example.ex.model

import java.util.Date
import javax.persistence.*

@Entity
@Table
class EmployeeHourReport{
    @Id
    @Column(name = "Visa")
    private var id:Long = 0

    @OneToOne
    @MapsId
    @JoinColumn(name = "Visa")
    lateinit var employMetaInfo:EmployMetaInfo

    @Column(name = "Name")
    private var name: String = ""

    @Column(name = "First_Name")
    private var firstName: String = ""

    @Column(name = "Last_Name")
    private var lastName: String = ""

    @Column(name = "Division")
    private var division: String = ""

    @Column(name = "Is_Mgr")
    private var isMgr: String = ""

    @Column(name = "Groups")
    private var group:String = ""

    @Column(name = "Working_Hours")
    private var workingHours: Double = 0.0

    @Column(name = "Worked_Hours")
    private var workedHours: Double = 0.0

    @Column(name = "Difference")
    private var difference: Double = 0.0

    @Column(name = "Cumul_Diff")
    private var cumulDiff: Double = 0.0

    @Column(name = "Vac_Left")
    private var vecLeft: Double = 0.0

    @Column(name = "Entrance")
    private var entrance:Date = TODO()

    @Column(name = "Resignation")
    private var resignation:Int = 0

    @Column(name = "CH_Prd")
    private var chPrd:Double = 0.0

    @Column(name = "CHPrd")
    private var CHPrd:Double = 0.0

    @Column(name = "Absence")
    private var absence:Double = 0.0

    @Column(name = "Sort")
    private var sort: Int = 0

    @Column(name = "Forfait")
    private var forfait:Boolean = false

    @Column(name = "Part_Time")
    private var partTime: Int = 0

    @Column(name = "Calculated")
    private var calculated:Double = 0.0

    @Column(name = "Calculated_Vac_Left")
    private var calculatedVecLeft:Double = 0.0

    @Column(name = "Calculated_Is_Mgr")
    private var calculatedIsMgr:String

    @Column(name = "VN_Entry")
    private var vnEntry:Date

    @Column(name = "Calculated_Division")
    private var calculatedDivision:String

    @Column(name = "Calculated_Overview")
    private var calculatedOverview:String

    @Column(name = "Holidays")
    private var holidays:Int = 0

    @Column(name = "Illness")
    private var illness:Int = 0

    @Column(name = "Trainings")
    private var trainings:Int = 0

    @Column(name = "Onboaring")
    private var onboarding:Int = 0

    @Column(name = "Others")
    private var others:Int = 0

    @Column(name = "ConsolidatedHours")
    private var consolidatedHours:Double = 0.0
}
