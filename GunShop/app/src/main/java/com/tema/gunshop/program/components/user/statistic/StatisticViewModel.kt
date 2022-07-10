package com.tema.gunshop.program.components.user.statistic

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.GunDao
import com.tema.gunshop.program.database.dao.PlatformDao
import com.tema.gunshop.program.database.dao.UserDao
import com.tema.gunshop.program.database.entity.GunEntity
import com.tema.gunshop.program.database.entity.PlatformEntity
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val gunDao: GunDao,
    private val userDao: UserDao,
    private val platformDao: PlatformDao,
) : BaseViewModel(appStateHandler) {

    var theMostPopularGames by mutableStateOf(listOf<GunEntity>())
        private set

    var theMostExpensiveGames by mutableStateOf(listOf<GunEntity>())
        private set

    var usersWithTheBiggestGamesCount by mutableStateOf(listOf<UserEntity>())
        private set

    var theMostPopularPlatforms by mutableStateOf(listOf<PlatformEntity>())
        private set

    fun onLaunched() {
        updateTheMostPopularGames()
        updateUsersByGamesCount()
        updateTheMostExpensiveGames()
        updateTheMostPopularPlatforms()
    }

    private fun updateTheMostPopularGames() {
        launch {
            theMostPopularGames = gunDao.getTheMostPopularGamesIds(5).map { gameId ->
                gunDao.getGameById(gameId)
            }.filterNotNull()
        }
    }

    private fun updateUsersByGamesCount() {
        launch {
            usersWithTheBiggestGamesCount = userDao.getUsersIdsByGamesCount(5).map { userId ->
                userDao.getUserById(userId)
            }.filterNotNull()
        }
    }

    private fun updateTheMostExpensiveGames() {
        launch {
            theMostExpensiveGames = gunDao.getTheMostExpensiveGames(5)
        }
    }

    private fun updateTheMostPopularPlatforms() {
        launch {
            theMostPopularPlatforms = platformDao.getTheMostPopularPlatformsIds(3)
        }
    }
}