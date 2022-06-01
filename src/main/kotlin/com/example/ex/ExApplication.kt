package com.example.ex

import com.example.PilotReadingService.model.EmployMetaInfo
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.FileReader


@SpringBootApplication
class ExApplication

fun main(args: Array<String>) {
    runApplication<ExApplication>(*args)
    val fileName = "C:\\Users\\hoah\\Desktop\\Ex\\Ex\\EmployeeMetaInfo-sample.csv"

    val beans: MutableList<EmployMetaInfo?>? = CsvToBeanBuilder<Any?>(FileReader(fileName))
        .withType(EmployMetaInfo::class.java)
        .build()
        .parse() as MutableList<EmployMetaInfo?>?

    beans?.onEach {
        println(it?.name)
    }
}
