package com.tema.gunshop.program.components.user.allgames

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.ChequeDao
import com.tema.gunshop.program.database.dao.DeveloperDao
import com.tema.gunshop.program.database.dao.GunDao
import com.tema.gunshop.program.database.dao.UserDao
import com.tema.gunshop.program.database.entity.ChequeEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.entity.dataclasses.Game
import com.tema.gunshop.system.entity.mappers.GameEntityToGameMapper
import com.tema.gunshop.system.navigation.AppStateHandler
import com.tema.gunshop.system.utils.extentions.writeToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AllGamesViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val gunDao: GunDao,
    private val userDao: UserDao,
    private val developerDao: DeveloperDao,
    private val chequeDao: ChequeDao,
    private val gameEntityToGameMapper: GameEntityToGameMapper,
) : BaseViewModel(appStateHandler) {

    private var context: Context? = null

    var games by mutableStateOf<List<Game>>(emptyList())
        private set

    var myGames by mutableStateOf<List<Game>>(emptyList())
        private set

    fun onContextAttached(context: Context) {
        this.context = context
    }

    fun onContextDetached() {
        context = null
    }

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

        launch {
            myGames = chequeDao.getGameIdsByUserId(appData.userEntity?.id ?: 0).map { gameId ->
                val gameEntity = gunDao.getGameById(gameId)!!
                val developerName = developerDao.getDeveloperNameById(gameEntity.developerId)

                gameEntityToGameMapper.map(gameEntity, developerName)
            }
        }
    }

    fun onBuyGameClicked(game: Game) {
        val user = appData.userEntity!!

        launch {
            val chequeEntity = ChequeEntity(
                userId = appData.userEntity!!.id,
                gunId = game.id,
            )

            chequeDao.insertAll(chequeEntity)

            myGames = mutableListOf<Game>().apply {
                addAll(myGames)
                add(games.find { it.id == game.id }!!)
            }

            Date().also { date ->
                context?.writeToFile(
                    fileName = "Game_cheque_${date.time}.txt",
                    text = "Ви, ${user.firstName} ${user.lastName}, " +
                            "купили зброю ${game.name} у постачальника ${game.developerName} за ${game.price} грн." +
                            "\nВаш рахунок: ${user.balance - game.price} грн." +
                            "\nДата: $date",
                )
            }
        }

        launch {
            val newUser = (user.copy(balance = user.balance - game.price))

            userDao.update(newUser)
            appStateHandler.saveUserEntity(newUser)
        }
    }
}