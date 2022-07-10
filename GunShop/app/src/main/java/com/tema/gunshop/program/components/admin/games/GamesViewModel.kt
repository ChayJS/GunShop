package com.tema.gunshop.program.components.admin.games

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.DeveloperDao
import com.tema.gunshop.program.database.dao.GunDao
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.entity.dataclasses.Game
import com.tema.gunshop.system.entity.enums.GameSortBy
import com.tema.gunshop.system.entity.mappers.GameEntityToGameMapper
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val gunDao: GunDao,
    private val developerDao: DeveloperDao,
    private val gameEntityToGameMapper: GameEntityToGameMapper,
) : BaseViewModel(appStateHandler) {

    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    var gameSortBy by mutableStateOf(GameSortBy.Id)
        private set

    fun onLaunched() {
        launch {
            val gamesMutableList = mutableListOf<Game>()

            gunDao.getAll().forEach { gameEntity ->
                val game = gameEntityToGameMapper.map(
                    gunEntity = gameEntity,
                    developerName = developerDao.getDeveloperNameById(gameEntity.developerId),
                )

                gamesMutableList.add(game)
            }
            games = gamesMutableList.toList()
        }
    }

    fun onSortClicked(gameSortBy: GameSortBy) {
        this.gameSortBy = gameSortBy

        games = when (gameSortBy) {

            GameSortBy.Id -> games.sortedBy { it.id }
            GameSortBy.Name -> games.sortedBy { it.name }
            GameSortBy.Developer -> games.sortedBy { it.developerName }
            GameSortBy.Genre -> games.sortedBy { it.genre }
            GameSortBy.MinAge -> games.sortedBy { it.minAge }
            GameSortBy.Price -> games.sortedBy { it.price }
            GameSortBy.Rating -> games.sortedBy { it.rating }
        }
    }

    fun onDeleteGameClicked(game: Game) {
        launch {
            val gameEntity = gunDao.getGameById(game.id)
            gunDao.delete(gameEntity)

            games = mutableListOf<Game>().apply {
                addAll(games)
                remove(game)
            }
        }
    }
}