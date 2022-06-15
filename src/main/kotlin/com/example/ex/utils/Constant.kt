package com.example.ex.utils

import com.google.gson.Gson
import com.querydsl.jpa.impl.JPAQueryFactory
import kotlinx.serialization.json.*
import java.util.HashMap
import javax.persistence.EntityManager

object Constant {

    const val MONTH_YEAR = "Month"
    const val SUB_PROJECT = "Subproject"
    const val VISA = "Visa"


    const val YEAR = "year"
    const val DATE = "Date"
    const val GRP_PRJ = "GRP-PRJ"
    const val P = "P"
    const val CODE = "Code"
    const val HRS = "Hrs"
    const val IS_VN = "Vn ?"
    const val PROJECT = "Project"


    fun getJpaQuery(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }


    val format = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }


    val gson = Gson()
    const val FORMAT_STRING_DATE = "yyyy-MM-dd"
    private fun Any?.toJsonElement(): JsonElement {
        return when (this) {
            is Number -> JsonPrimitive(this)
            is Boolean -> JsonPrimitive(this)
            is String -> JsonPrimitive(this)
            is Array<*> -> this.toJsonArray()
            is List<*> -> this.toJsonArray()
            is Map<*, *> -> this.toJsonObject()
            is JsonElement -> this
            else -> JsonNull
        }
    }

    private fun Array<*>.toJsonArray(): JsonArray {
        val array = mutableListOf<JsonElement>()
        this.forEach { array.add(it.toJsonElement()) }
        return JsonArray(array)
    }

    private fun List<*>.toJsonArray(): JsonArray {
        val array = mutableListOf<JsonElement>()
        this.forEach { array.add(it.toJsonElement()) }
        return JsonArray(array)
    }

    fun Map<*, *>.toJsonObject(): JsonObject {
        val map = mutableMapOf<String, JsonElement>()
        this.forEach {
            if (it.key is String) {
                map[it.key as String] = it.value.toJsonElement()
            }
        }
        return JsonObject(map)
    }
}