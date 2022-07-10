package com.tema.gunshop.program.components.create.developer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.DeveloperDao
import com.tema.gunshop.program.database.entity.DeveloperEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateDeveloperViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val developerDao: DeveloperDao,
) : BaseViewModel(appStateHandler) {

    var companyName by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmedPassword by mutableStateOf("")
        private set

    var companyNameError by mutableStateOf("")
        private set

    var loginError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var confirmedPasswordError by mutableStateOf("")
        private set

    fun onDisposed() {
        companyName = ""
        login = ""
        password = ""
        confirmedPassword = ""
        companyNameError = ""
        loginError = ""
        passwordError = ""
        confirmedPasswordError = ""
    }

    fun onCompanyNameUpdated(companyName: String) {
        this.companyName = companyName
        companyNameError = ""
    }

    fun onLoginUpdated(login: String) {
        this.login = login
        loginError = ""
    }

    fun onPasswordUpdated(password: String) {
        this.password = password
        passwordError = ""
    }

    fun onConfirmedPasswordUpdated(confirmedPassword: String) {
        this.confirmedPassword = confirmedPassword
        confirmedPasswordError = ""
    }

    fun onCreateDeveloperClicked() {

        launch {

            if (isLoginCorrect() && isPasswordsCorrect()) {

                val developerEntity = DeveloperEntity(
                    name = companyName,
                    login = login,
                    password = password,
                )

                developerDao.insertAll(developerEntity)

                appStateHandler.back()
            }
        }
    }

    private fun isLoginCorrect(): Boolean {
        if (login.length < 6) {
            loginError = "Логін повинен мати більше 5 символів"
            return false
        }

        return true
    }

    private fun isPasswordsCorrect(): Boolean {
        if (password.length < 6) {
            passwordError = "Пароль повинен мати більше 5 символів"
            return false
        }

        if (password != confirmedPassword) {
            passwordError = "Паролі не співпадають"
            confirmedPasswordError = "Паролі не співпадають"
            return false
        }

        return true
    }
}