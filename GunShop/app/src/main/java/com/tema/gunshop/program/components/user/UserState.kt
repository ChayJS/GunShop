package com.tema.gunshop.program.components.user

sealed interface UserState {

    object Profile : UserState

    object MyGames : UserState

    object AllGames : UserState

    object Statistic : UserState
}