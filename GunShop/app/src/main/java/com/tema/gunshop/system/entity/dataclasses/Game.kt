package com.tema.gunshop.system.entity.dataclasses

data class Game(
    val id: Int,
    val developerId: Int,
    val developerName: String,
    val name: String,
    val genre: String,
    val price: Int,
    val minAge: Int,
    val rating: Int,
)
