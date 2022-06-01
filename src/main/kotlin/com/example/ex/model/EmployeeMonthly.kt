package com.example.PilotReadingService.model

import com.opencsv.bean.CsvBindByName
import java.util.Date
import javax.persistence.*


@Entity
@Table(name = "EmployeeMonthlyVertec")
class EmployeeMonthly {
    @Id
    @Column(name = "Visa")
    private var id:String = ""

    @OneToOne
    @MapsId
    @JoinColumn(name = "Visa")
    lateinit var metaInfo: EmployMetaInfo

    @Column(name = "Date")
    @CsvBindByName(column = "Date")
    private lateinit var date:Date

    @Column(name = "Code")
    @CsvBindByName(column = "Code")
    private var code:String = ""

    @Column(name = "Hrs")
    @CsvBindByName(column = "Hrs")
    private var hours: Int = 0

    @Column(name = "Comment")
    @CsvBindByName(column = "Comment")
    private var comment:String = ""

    @Column(name = "Description")
    @CsvBindByName(column = "Description")
    private var description:String = ""

    @Column(name = "VN")
    @CsvBindByName(column = "VN ?")
    private var vn: Boolean = false

    @Column(name = "Subproject")
    @CsvBindByName(column = "Subproject")
    private var subProject:String = ""

    @Column(name = "Subproject_Name")
    @CsvBindByName(column = "Subproject name")
    private var subprojectName:String = ""

    @Column(name = "Project")
    @CsvBindByName(column = "Project")
    private var project:Long = 0

    @Column(name = "Project_Name")
    @CsvBindByName(column = "Project name")
    private var projectName:String = ""

    @Column(name = "VN_Hrs")
    @CsvBindByName(column = "VN Hrs")
    private var vnHrs:Int = 0

    @Column(name = "CH_Hrs")
    @CsvBindByName(column = "CH Hrs")
    private var chHrs:Int = 0

    @Column(name = "Unique")
    @CsvBindByName(column = "Unique")
    private var unique:Int = 0

    @Column(name = "Calculated_Subproject_Name")
    @CsvBindByName(column = "Calculated subproject name")
    private var calculatedSubprojectName:String = ""

    @Column(name = "Division")
    @CsvBindByName(column = "Division")
    private var division:String = ""

}