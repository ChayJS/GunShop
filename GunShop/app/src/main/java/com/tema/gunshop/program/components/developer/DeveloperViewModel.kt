package com.tema.gunshop.program.components.developer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.GunDao
import com.tema.gunshop.program.database.entity.GunEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.program.components.RootState
import com.tema.gunshop.program.components.create.CreateState
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeveloperViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val gunDao: GunDao,
) : BaseViewModel(appStateHandler) {

    var developerGames by mutableStateOf(emptyList<GunEntity>())
        private set

    fun onLaunched() {
        launch {
            developerGames = gunDao.getGamesByDeveloperId(appData.developerEntity!!.id)
        }
    }

    fun onCreateGameClicked() {
        appStateHandler
            .open(copyCurrentState = true)
            .rootState(RootState.Create)
            .createState(CreateState.CreateGame)
            .commit()
    }
}