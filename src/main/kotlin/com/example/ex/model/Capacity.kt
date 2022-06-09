package com.example.ex.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.opencsv.bean.CsvBindByName
import javax.persistence.*

@Entity
@Table(name = "Capacity")
class Capacity: EntitySuper() {
    @Id
    var visa:String = ""

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @MapsId
    @CsvBindByName(column = "Visa")
    @JsonBackReference
    @JoinColumn(name = "visa", nullable = true, foreignKey = ForeignKey(name = "fk_capacity_employee"))
    lateinit var metaInfo: EmployeeMetaInfo

    @Column(name = "Reserve_1")
    @CsvBindByName(column = "reserve 1")
    var reverse1:String = ""

    @Column(name = "Reserve_2")
    @CsvBindByName(column = "reserve 2")
    var reverse2:String = ""

    @Column(name = "Reserve_3")
    @CsvBindByName(column = "reserve 3")
    var reverse3:String = ""

    @Column(name = "Reserve_4")
    @CsvBindByName(column = "reserve 4")
    var reverse4:String = ""

    @Embedded
    var month: Month? = null
}