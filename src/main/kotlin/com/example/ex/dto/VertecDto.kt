package com.example.ex.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.persistence.Id

@Serializable
class VertecDto {
    @SerialName("Month")
    var monthYear: String? = null

    @SerialName("Visa")
    var visaVertec: String? = null

    @SerialName("Subproject")
    var subproject: String? = null

//    constructor(monthYear: String?, visa: String?, subproject: String?) {
//        this.monthYear = monthYear
//        this.visao = visa
//        this.subproject = subproject
//    }


}