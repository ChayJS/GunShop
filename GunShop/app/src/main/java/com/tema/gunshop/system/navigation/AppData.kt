package com.tema.gunshop.system.navigation

import com.tema.gunshop.program.database.entity.DeveloperEntity
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.system.entity.enums.Role

data class AppData(
    val role: Role? = null,
    val userEntity: UserEntity? = null,
    val developerEntity: DeveloperEntity? = null,
)
