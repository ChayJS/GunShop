package com.tema.gunshop.program.components

import com.tema.gunshop.program.database.dao.*
import com.tema.gunshop.program.database.entity.ChequeEntity
import com.tema.gunshop.program.database.entity.DeveloperEntity
import com.tema.gunshop.program.database.entity.GunEntity
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val userDao: UserDao,
    private val developerDao: DeveloperDao,
    private val gunDao: GunDao,
    private val chequeDao: ChequeDao,
    private val platformDao: PlatformDao,
) : BaseViewModel(appStateHandler) {

    init {
        appStateHandler
            .clearBackStack()
            .open(copyCurrentState = false)
            .rootState(RootState.Role)
            .commit()

        fillDatabaseMockData()
    }

    fun onBackClicked(onAppStateStackEmpty: () -> Unit) {
        appStateHandler.back(onAppStateStackEmpty)
    }

    private fun fillDatabaseMockData() {
        launch {

            if (userDao.getAll().isNullOrEmpty()) {

                val userEntities = arrayOf(

                    UserEntity(
                        firstName = "Артём",
                        lastName = "Ильясов",
                        login = "Nagibator228",
                        password = "111111",
                        age = 19,
                        balance = 10000,
                    ),

                    UserEntity(
                        firstName = "Алекс",
                        lastName = "Мошч",
                        login = "MicroMolekula",
                        password = "111111",
                        age = 19,
                        balance = 5000,
                    ),

                    UserEntity(
                        firstName = "Денчик",
                        lastName = "Машина",
                        login = "CoolDen",
                        password = "111111",
                        age = 10,
                        balance = 3000,
                    ),

                    UserEntity(
                        firstName = "Глист",
                        lastName = "Звичайний",
                        login = "=======>",
                        password = "111111",
                        age = 16,
                        balance = 100,
                    ),
                )

                userDao.insertAll(*userEntities)
            }

            if (developerDao.getAll().isNullOrEmpty()) {

                val developerEntities = arrayOf(

                    DeveloperEntity(
                        name = "JAjajjajaja",
                        login = "Wargaming",
                        password = "111111",
                    ),

                    DeveloperEntity(
                        name = "CD Project",
                        login = "CDProject",
                        password = "111111",
                    ),

                    DeveloperEntity(
                        name = "Larian",
                        login = "Larian",
                        password = "111111",
                    ),

                    DeveloperEntity(
                        name = "Plarium",
                        login = "Plarium",
                        password = "111111",
                    ),

                    DeveloperEntity(
                        name = "SuperCell",
                        login = "SuperCell",
                        password = "111111",
                    ),
                )

                developerDao.insertAll(*developerEntities)
            }

            if (gunDao.getAll().isNullOrEmpty()) {

                val gamesEntities = arrayOf(

                    GunEntity(
                        developerId = 1,
                        name = "Glock-18",
                        genre = "Gun",
                        price = 10000,
                        minAge = 19,
                        rating = 4,
                    ),

                    GunEntity(
                        developerId = 2,
                        name = "USP-S",
                        genre = "Gun",
                        price = 50000,
                        minAge = 18,
                        rating = 6,
                    ),

                    GunEntity(
                        developerId = 3,
                        name = "M4A4",
                        genre = "Weapon",
                        price = 90000,
                        minAge = 22,
                        rating = 8,
                    ),

                    GunEntity(
                        developerId = 4,
                        name = "AK-47",
                        genre = "Weapon",
                        price = 100000,
                        minAge = 25,
                        rating = 10,
                    ),

                    GunEntity(
                        developerId = 5,
                        name = "AWP",
                        genre = "Sniper weapon",
                        price = 299999,
                        minAge = 27,
                        rating = 9,
                    ),
                )

                gunDao.insertAll(*gamesEntities)
            }

            if (chequeDao.getAll().isNullOrEmpty()) {

                val chequeEntities = arrayOf(

                    ChequeEntity(
                        userId = 1,
                        gunId = 1,
                    ),

                    ChequeEntity(
                        userId = 2,
                        gunId = 1,
                    ),

                    ChequeEntity(
                        userId = 2,
                        gunId = 2,
                    ),

                    ChequeEntity(
                        userId = 3,
                        gunId = 1,
                    ),
                )

                chequeDao.insertAll(*chequeEntities)
            }
        }
    }
}