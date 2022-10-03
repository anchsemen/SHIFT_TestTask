package com.example.shift

import java.io.Serializable

data class User(
    val surname: String,
    val name: String,
    val date: String
): Serializable
