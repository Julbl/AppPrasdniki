package com.example.appprasdniki

import java.io.Serializable
import java.util.Date

data class Holiday(
    val name: String,
    val date: String,
    val description: String,
    val imageResource: Int
) : Serializable
