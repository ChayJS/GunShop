package com.tema.gunshop.program.components.user

import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
) : BaseViewModel(appStateHandler) {

    fun onProfileSelected() {
        appStateHandler
            .replace(copyCurrentState = true)
            .userState(UserState.Profile)
            .commit()
    }

    fun onMyGamesSelected() {
        appStateHandler
            .replace(copyCurrentState = true)
            .userState(UserState.MyGames)
            .commit()
    }

    fun onAllGamesSelected() {
        appStateHandler
            .replace(copyCurrentState = true)
            .userState(UserState.AllGames)
            .commit()
    }

    fun onStatisticSelected() {
        appStateHandler
            .replace(copyCurrentState = true)
            .userState(UserState.Statistic)
            .commit()
    }
}