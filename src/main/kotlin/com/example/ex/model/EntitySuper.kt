package com.example.ex.model

import javax.persistence.Column
import javax.persistence.MappedSuperclass
import javax.persistence.Version

@MappedSuperclass
open class EntitySuper() {
    @Version
    @Column(name = "version")
    private val version: Int = 0
}