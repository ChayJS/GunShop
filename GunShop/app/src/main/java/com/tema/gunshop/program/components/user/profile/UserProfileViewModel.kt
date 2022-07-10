package com.tema.gunshop.program.components.user.profile

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import com.tema.gunshop.program.database.dao.UserDao
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.navigation.AppStateHandler
import com.tema.gunshop.system.utils.extentions.writeToFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val userDao: UserDao,
) : BaseViewModel(appStateHandler) {

    private var context: Context? = null

    var firstName by mutableStateOf("")
        private set

    var secondName by mutableStateOf("")
        private set

    var age by mutableStateOf("")
        private set

    var money by mutableStateOf("")
        private set

    var firstNameError by mutableStateOf("")
        private set

    var secondNamError by mutableStateOf("")
        private set

    var ageError by mutableStateOf("")
        private set

    var moneyError by mutableStateOf("")
        private set

    fun onContextAttached(context: Context) {
        this.context = context
    }

    fun onContextDetached() {
        context = null
    }

    fun onLaunched() {
        firstName = appData.userEntity?.firstName.orEmpty()
        secondName = appData.userEntity?.lastName.orEmpty()
        age = appData.userEntity?.age.toString()
    }

    fun onFirstNameUpdated(value: String) {
        firstNameError = ""
        firstName = value
    }

    fun onSecondNameUpdated(value: String) {
        secondNamError = ""
        secondName = value
    }

    fun onAgeUpdated(value: String) {
        ageError = ""

        if (value.isDigitsOnly()) {
            age = value
        } else {
            ageError = "Вік повинен мати тільки цифри"
        }
    }

    fun onMoneyUpdated(value: String) {
        if (value.length > 6) {
            return
        }

        moneyError = ""

        if (value.isDigitsOnly()) {
            money = value
        } else {
            moneyError = "Сума повинна мати тільки цифри"
        }
    }

    fun onSaveButtonClicked() {
        if (firstName.isBlank()) {
            firstNameError = "Пусте поле"
            return
        }

        if (secondName.isBlank()) {
            secondNamError = "Пустое поле"
            return
        }

        if (try {
                age.toInt()
            } catch (e: Exception) {
                0
            } <= 0
        ) {
            ageError = "Неправильний вік"
            return
        }

        launch {
            val user = appData.userEntity!!
            val newUser =
                (user.copy(firstName = firstName, lastName = secondName, age = age.toInt()))

            userDao.update(newUser)
            appStateHandler.saveUserEntity(newUser)
        }
    }

    fun onAddToBalanceClicked() {
        if (money.isBlank()) {
            moneyError = "Введите суму пополнения"
            return
        }

        launch {
            val user = appData.userEntity!!
            val newUser = (user.copy(balance = user.balance + money.toInt()))

            userDao.update(newUser)
            appStateHandler.saveUserEntity(newUser)

            Date().also { date ->
                context?.writeToFile(
                    fileName = "Balance_update_${date.time}.txt",
                    text = "Ви, ${user.firstName} ${user.lastName}, поповніть рахунок на $money грн.\n$date",
                )
            }

            money = ""
        }
    }
}