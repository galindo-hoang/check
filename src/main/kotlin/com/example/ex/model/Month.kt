package com.example.ex.model

import com.opencsv.bean.CsvBindByName
import javax.persistence.*

@Embeddable
open class Month {
    @Column(name = "Jan")
    @CsvBindByName(column = "Jan")
    private var jan: Int? = null

    @Column(name = "Feb")
    @CsvBindByName(column = "Feb")
    private var feb: Int? = null

    @Column(name = "Mar")
    @CsvBindByName(column = "Mar")
    private var mar: Int? = null

    @Column(name = "Apr")
    @CsvBindByName(column = "Apr")
    private var apr: Int? = null

    @Column(name = "May")
    @CsvBindByName(column = "May")
    private var may: Int? = null

    @Column(name = "Jun")
    @CsvBindByName(column = "Jun")
    private var jun: Int? = null

    @Column(name = "Jul")
    @CsvBindByName(column = "Jul")
    private var jul: Int? = null

    @Column(name = "Aug")
    @CsvBindByName(column = "Aug")
    private var aug: Int? = null

    @Column(name = "Sep")
    @CsvBindByName(column = "Sep")
    private var sep: Int? = null

    @Column(name = "Oct")
    @CsvBindByName(column = "Oct")
    private var oct: Int? = null

    @Column(name = "Nov")
    @CsvBindByName(column = "Nov")
    private var nov: Int? = null

    @Column(name = "Dec")
    @CsvBindByName(column = "Dec")
    private var dec: Int? = null

}