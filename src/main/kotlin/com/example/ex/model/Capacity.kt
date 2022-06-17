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

    @Column(name = "Department")
    var department: String = ""

    @Column(name = "StartDate")
    var startDate: Date? = null

    @Column(name = "EndDate")
    var endDate: Date? = null
}