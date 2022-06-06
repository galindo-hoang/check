package com.example.ex.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.opencsv.bean.CsvBindByName
import javax.persistence.*

@Entity
@Table(name = "EmployRole")
class EmployeeRole {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long = 0

    @Column(name = "Employee_Nr")
    @CsvBindByName(column = "Employee Nr")
    var employeeNr:Int? = null

    @Column(name = "Active")
    @CsvBindByName(column = "Active")
    var active: Boolean? = null

    @Column(name = "Last")
    @CsvBindByName(column = "Last")
    var last: String = ""

    @Column(name = "First")
    @CsvBindByName(column = "First")
    var first:String = ""

    @Column(name = "Contract")
    @CsvBindByName(column = "Contract")
    var contract:Char? = null

    @Column(name = "Forfait")
    @CsvBindByName(column = "Forfait")
    var forfait:Char? = null

    @Column(name = "User_Level")
    @CsvBindByName(column = "User Level")
    var userLevel:String = ""

    @Column(name = "Level")
    @CsvBindByName(column = "Level")
    var level:String = ""

    @Column(name = "Sub_Level")
    @CsvBindByName(column = "Sub-level")
    var subLevel:String = ""

    @Column(name = "Part_Time")
    @CsvBindByName(column = "Part-time")
    var partTime:Double? = null

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "Supervisor", nullable = true, foreignKey = ForeignKey(name = "fk_roleSupervisor_employee"))
    @CsvBindByName(column = "Supervisor")
    @JsonBackReference
    var supervisor:EmployeeMetaInfo? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "Abbreviation", nullable = true, foreignKey = ForeignKey(name = "fk_roleAbbreviation_employee"))
    @CsvBindByName(column = "Abbreviation")
    @JsonBackReference
    var abbreviation:EmployeeMetaInfo? = null
}