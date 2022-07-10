package com.tema.gunshop.program.components.create

sealed interface CreateState {

    object CreateUser : CreateState

    object CreateDeveloper : CreateState

    object CreateGame : CreateState
}