package com.example.ex.model

import com.opencsv.bean.CsvBindByName
import javax.persistence.*

@Entity
@Table(name = "Capacity")
class Capacity {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @CsvBindByName(column = "Visa")
    var id:Long = 0

//    @OneToOne(cascade = [CascadeType.ALL])
//    @MapsId
//    @JoinColumn(name = "Visa")
    @Column(name = "Visa")
    @CsvBindByName(column = "Visa")
    var metaInfo: String = ""

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
    lateinit var month: Month
}