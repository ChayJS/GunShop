package com.tema.gunshop.program.components.admin

sealed interface AdminState {

    object Users : AdminState

    object Managers : AdminState

    object Games : AdminState
}