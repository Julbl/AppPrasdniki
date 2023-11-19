package com.example.appprasdniki

import java.io.Serializable

data class Holiday(
    val name: String,
    val date: String,
    val description: String,
    val imageResource: Int
) : Serializable
