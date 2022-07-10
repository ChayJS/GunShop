package com.tema.gunshop.program.components.admin

import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.program.components.RootState
import com.tema.gunshop.program.components.create.CreateState
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
) : BaseViewModel(appStateHandler) {

    fun onUsersSelected() {
        appStateHandler
            .replace(copyCurrentState = true)
            .adminState(AdminState.Users)
            .commit()
    }

    fun onDevelopersSelected() {
        appStateHandler
            .replace(copyCurrentState = true)
            .adminState(AdminState.Managers)
            .commit()
    }

    fun onGamesSelected() {
        appStateHandler
            .replace(copyCurrentState = true)
            .adminState(AdminState.Games)
            .commit()
    }

    fun onCreateClicked() {
        val appStateBuilder = appStateHandler
            .open(copyCurrentState = true)
            .rootState(RootState.Create)

        when (appState.adminState) {

            AdminState.Users -> {
                appStateBuilder.createState(CreateState.CreateUser)
            }

            AdminState.Managers -> {
                appStateBuilder.createState(CreateState.CreateDeveloper)
            }

            AdminState.Games -> {
                appStateBuilder.createState(CreateState.CreateGame)
            }

            else -> Unit
        }

        appStateBuilder.commit()
    }
}