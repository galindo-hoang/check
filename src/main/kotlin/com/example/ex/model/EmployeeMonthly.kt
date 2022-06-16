package com.example.ex.model

import com.opencsv.bean.CsvBindByName
import java.sql.Date
import javax.persistence.*


@Entity
@Table(name = "EmployeeMonthlyVertec")
class EmployeeMonthly : EntitySuper() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Visa", nullable = true, foreignKey = ForeignKey(name = "fk_monthly_employee"))
//    @JsonBackReference
    lateinit var metaInfo: EmployeeMetaInfo

    @Column(name = "Date")
    var date:Date? = null

    @Column(name = "Code")
    var code:String = ""

    @Column(name = "Hrs")
    var hours: Double = 0.0

    @Column(name = "Comment")
    var comment:String = ""

    @Column(name = "Description")
    var description:String = ""

    @Column(name = "VN")
    var vn: Boolean = false

    @Column(name = "Subproject")
    var subProject:String = ""

    @Column(name = "Subproject_Name")
    var subprojectName:String = ""

    @Column(name = "Project")
    var project:String = ""

    @Column(name = "Project_Name")
    var projectName:String = ""

    @Column(name = "VN_Hrs")
    var vnHrs:Double = 0.0

    @Column(name = "CH_Hrs")
    var chHrs:Double = 0.0

    @Column(name = "Uniques")
    var unique:Int = 0

    @Column(name = "Calculated_Subproject_Name")
    var calculatedSubprojectName:String = ""

    @Column(name = "Division")
    var division:String = ""

    @Column(name = "ProjectGroup")
    var projectGroup:String? = null
}