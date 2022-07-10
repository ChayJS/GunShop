package com.tema.gunshop.program.components.user.mygames

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.ChequeDao
import com.tema.gunshop.program.database.dao.DeveloperDao
import com.tema.gunshop.program.database.dao.GunDao
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.entity.dataclasses.Game
import com.tema.gunshop.system.entity.mappers.GameEntityToGameMapper
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyGamesViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val chequeDao: ChequeDao,
    private val gunDao: GunDao,
    private val developerDao: DeveloperDao,
    private val gameEntityToGameMapper: GameEntityToGameMapper,
) : BaseViewModel(appStateHandler) {

    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    fun onLaunched() {
        launch {
            games = chequeDao.getGameIdsByUserId(appData.userEntity?.id ?: 0).map { gameId ->
                val gameEntity = gunDao.getGameById(gameId)
                val developerName = developerDao.getDeveloperNameById(gameEntity!!.developerId)

                gameEntityToGameMapper.map(gameEntity, developerName)
            }
        }
    }
}