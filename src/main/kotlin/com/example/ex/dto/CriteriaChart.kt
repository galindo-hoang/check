package com.example.ex.dto

data class CriteriaChart(
    val projects: List<String> = listOf(),
    val start: String = "",
    val end: String = ""
)
