package com.example.ex.model

import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EntitySuper
import com.fasterxml.jackson.annotation.JsonBackReference
import com.opencsv.bean.CsvBindByName
import java.sql.Date
import javax.persistence.*

@Entity
@Table(name = "Capacity")
class Capacity: EntitySuper() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int = 0

    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "visa", nullable = true, foreignKey = ForeignKey(name = "fk_capacity_employee"))
    lateinit var visa: EmployeeMetaInfo

//    @Column(name = "Reserve_1")
//    @CsvBindByName(column = "reserve 1")
//    var reverse1:String = ""
//
//    @Column(name = "Reserve_2")
//    @CsvBindByName(column = "reserve 2")
//    var reverse2:String = ""
//
//    @Column(name = "Reserve_3")
//    @CsvBindByName(column = "reserve 3")
//    var reverse3:String = ""
//
//    @Column(name = "Reserve_4")
//    @CsvBindByName(column = "reserve 4")
//    var reverse4:String = ""
//
//    @Embedded
//    var month: Month? = null

    @Column(name = "Department")
    var department: String = ""

    @Column(name = "StartDate")
    var startDate: Date? = null

    @Column(name = "EndDate")
    var endDate: Date? = null
}