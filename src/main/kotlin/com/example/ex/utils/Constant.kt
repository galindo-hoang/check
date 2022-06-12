package com.example.ex.utils

import com.querydsl.jpa.impl.JPAQueryFactory
import kotlinx.serialization.json.*
import java.util.HashMap
import javax.persistence.EntityManager

object Constant {

    const val MONTH_YEAR = "month"
    const val SUB_PROJECT = "Subproject"
    const val VISA = "visa"


    const val YEAR = "year"
    const val DATE = "date"
    const val GRP_PRJ = "grp-prj"
    const val P = "p"
    const val CODE = "code"
    const val HRS = "hrs"
    const val IS_VN = "IsVn"
    const val PROJECT = "project"


    fun getJpaQuery(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }


    val format = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

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