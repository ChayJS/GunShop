package com.tema.gunshop.program.components.role

import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.entity.enums.Role
import com.tema.gunshop.system.navigation.AppStateHandler
import com.tema.gunshop.program.components.RootState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoleViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
) : BaseViewModel(appStateHandler) {

    fun onLaunched() {
        appStateHandler.saveRole(null)
    }

    fun onEnterAsUserClicked() {
        appStateHandler.saveRole(Role.User)
        moveNext()
    }

    fun onEnterAsDeveloperClicked() {
        appStateHandler.saveRole(Role.Developer)
        moveNext()
    }

    fun onEnterAsAdminClicked() {
        appStateHandler.saveRole(Role.Admin)
        moveNext()
    }

    fun onMoveToRegistrationClicked() {
        appStateHandler
            .open(copyCurrentState = true)
            .rootState(RootState.Registration)
            .commit()
    }

    private fun moveNext() {
        appStateHandler
            .open(copyCurrentState = true)
            .rootState(RootState.Login)
            .commit()
    }
}