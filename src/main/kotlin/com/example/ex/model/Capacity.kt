package com.example.PilotReadingService.model

import com.opencsv.bean.CsvBindByName
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "Capacity")
class Capacity {
    @Id
    @Column(name = "Visa")
    @CsvBindByName(column = "Visa")
    private var id:String = ""

    @OneToOne
    @MapsId
    @JoinColumn(name = "Visa")
    @CsvBindByName(column = "Date")
    lateinit var metaInfo: EmployMetaInfo

    @Column(name = "Reverse_1")
    @CsvBindByName(column = "reserve 1")
    var reverse1:String = ""

    @Column(name = "Reverse_2")
    @CsvBindByName(column = "reserve 2")
    var reverse2:String = ""

    @Column(name = "Reverse_3")
    @CsvBindByName(column = "reserve 3")
    var reverse3:String = ""

    @Column(name = "Reverse_4")
    @CsvBindByName(column = "reserve 4")
    var reverse4:String = ""

    @Embedded
    private lateinit var month: Month
}