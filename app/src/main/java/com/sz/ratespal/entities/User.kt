package com.sz.ratespal.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: Int,
    val created: Int,
    val name: String,
    val email: String,
    val token: String
) {
    constructor() : this(0, 0, "", "", "")
}