package com.example.ex.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.opencsv.bean.CsvBindByName
import java.sql.Date
import javax.persistence.*


@Entity
@Table(name = "EmployeeMonthlyVertec")
class EmployeeMonthly: EntitySuper() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Visa", nullable = true, foreignKey = ForeignKey(name = "fk_monthly_employee"))
    @JsonBackReference
    lateinit var metaInfo: EmployeeMetaInfo

    @Column(name = "Date")
    @CsvBindByName(column = "Date")
    var date:Date? = null

    @Column(name = "Code")
    @CsvBindByName(column = "Code")
    var code:String = ""

    @Column(name = "Hrs")
    @CsvBindByName(column = "Hrs")
    var hours: Double = 0.0

    @Column(name = "Comment")
    @CsvBindByName(column = "Comment")
    var comment:String = ""

    @Column(name = "Description")
    @CsvBindByName(column = "Description")
    var description:String = ""

    @Column(name = "VN")
    @CsvBindByName(column = "VN ?")
    var vn: Boolean = false

    @Column(name = "Subproject")
    @CsvBindByName(column = "Subproject")
    var subProject:String = ""

    @Column(name = "Subproject_Name")
    @CsvBindByName(column = "Subproject name")
    var subprojectName:String = ""

    @Column(name = "Project")
    @CsvBindByName(column = "Project")
    var project:Long = 0

    @Column(name = "Project_Name")
    @CsvBindByName(column = "Project name")
    var projectName:String = ""

    @Column(name = "VN_Hrs")
    @CsvBindByName(column = "VN Hrs")
    var vnHrs:Int = 0

    @Column(name = "CH_Hrs")
    @CsvBindByName(column = "CH Hrs")
    var chHrs:Int = 0

    @Column(name = "Uniques")
    @CsvBindByName(column = "Unique")
    var unique:Int = 0

    @Column(name = "Calculated_Subproject_Name")
    @CsvBindByName(column = "Calculated subproject name")
    var calculatedSubprojectName:String = ""

    @Column(name = "Division")
    @CsvBindByName(column = "Division")
    var division:String = ""

}