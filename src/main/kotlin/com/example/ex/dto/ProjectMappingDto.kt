package com.example.ex.dto

import com.google.gson.annotations.SerializedName

class ProjectMappingDto {
    @SerializedName(value = "ProjectCode")
    var projectCode: String? = null

    @SerializedName(value = "ProjectGroup")
    var projectGroup: String? = null
}