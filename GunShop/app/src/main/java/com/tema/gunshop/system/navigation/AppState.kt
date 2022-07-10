package com.tema.gunshop.system.navigation

import com.tema.gunshop.program.components.RootState
import com.tema.gunshop.program.components.admin.AdminState
import com.tema.gunshop.program.components.create.CreateState
import com.tema.gunshop.program.components.user.UserState

data class AppState(
    val rootState: RootState? = null,
    val adminState: AdminState? = null,
    val userState: UserState? = null,
    val createState: CreateState? = null,
)