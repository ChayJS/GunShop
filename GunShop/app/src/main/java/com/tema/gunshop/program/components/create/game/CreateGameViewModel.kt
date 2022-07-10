package com.tema.gunshop.program.components.create.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.GunDao
import com.tema.gunshop.program.database.entity.GunEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CreateGameViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val gunDao: GunDao
) : BaseViewModel(appStateHandler) {

    var name by mutableStateOf("")
        private set

    var genre by mutableStateOf("")
        private set

    var price by mutableStateOf("")
        private set

    var minAge by mutableStateOf("")
        private set

    var nameError by mutableStateOf("")
        private set

    var genreError by mutableStateOf("")
        private set

    var priceError by mutableStateOf("")
        private set

    var minAgeError by mutableStateOf("")
        private set

    fun onNameUpdated(value: String) {
        name = value
    }

    fun onGenreUpdated(value: String) {
        genre = value
    }

    fun onPriceUpdated(value: String) {
        price = value
    }

    fun onMinAgeUpdated(value: String) {
        minAge = value
    }

    fun onCreateClicked() {
        launch {
            val gunEntity = GunEntity(
                developerId = appData.developerEntity!!.id,
                name = name,
                genre = genre,
                price = price.toInt(),
                minAge = minAge.toInt(),
                rating = Random.nextInt(1, 10),
            )

            gunDao.insertAll(gunEntity)

            appStateHandler.back()
        }
    }
}