package com.example.ex.model

import com.opencsv.bean.CsvBindByName
import org.slf4j.event.Level
import javax.persistence.*

@Entity
@Table(name = "EmployRole")
class EmployRole {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int = 0

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

//    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Abbreviation")
    @CsvBindByName(column = "Abbreviation")
    var abbreviation:String = ""

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
    var partTime:Double = 0.0

//    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "Supervisor")
    @CsvBindByName(column = "Supervisor")
    var supervisor:String = ""
}