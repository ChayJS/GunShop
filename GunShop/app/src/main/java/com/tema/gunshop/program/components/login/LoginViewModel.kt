package com.tema.gunshop.program.components.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import com.tema.gunshop.program.database.dao.DeveloperDao
import com.tema.gunshop.program.database.dao.UserDao
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.program.components.RootState
import com.tema.gunshop.program.components.admin.AdminState
import com.tema.gunshop.program.components.user.UserState
import com.tema.gunshop.system.entity.enums.Role
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val userDao: UserDao,
    private val developerDao: DeveloperDao,
) : BaseViewModel(appStateHandler) {

    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var loginError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    // TODO: 02.01.2022 - Delete
    fun onLaunched() {

        when (appData.role) {

            Role.User -> {
                login = "CoolDen"
                password = "111111"
            }

            Role.Admin -> {
                login = "admin"
                password = "admin"
            }

            Role.Developer -> {
                login = "CDProject"
                password = "111111"
            }

            else -> Unit
        }
    }

    fun onDisposed() {
        login = ""
        password = ""
    }

    fun onLoginUpdated(login: String) {
        this.login = login
        loginError = ""
    }

    fun onPasswordUpdated(password: String) {
        this.password = password
        passwordError = ""
    }

    fun onLoginClicked() {

        when (appData.role) {

            Role.User -> loginUser()

            Role.Developer -> loginDeveloper()

            Role.Admin -> loginAdmin()

            else -> {}
        }
    }

    fun onMoveToRegistrationClicked() {
        appStateHandler
            .open(copyCurrentState = true)
            .rootState(RootState.Registration)
            .commit()
    }

    private fun loginUser() {
        if (!validateUser()) return

        launch {
            val userEntity = userDao.getUserByLoginAndPassword(login, password)

            if (userEntity == null) {
                loginError = "Неправильний логін або пароль"
                passwordError = "Неправильний логін або пароль"
            } else {
                appStateHandler
                    .saveUserEntity(userEntity)
                    .open(copyCurrentState = true)
                    .rootState(RootState.User)
                    .userState(UserState.Profile)
                    .commit()
            }
        }
    }

    private fun loginDeveloper() {
        if (!validateDeveloper()) return

        launch {
            val developerEntity = developerDao.getDeveloperByLoginAndPassword(login, password)

            if (developerEntity == null) {
                loginError = "Неправильний логін або пароль"
                passwordError = "Неправильний логін або пароль"
            } else {
                appStateHandler
                    .saveDeveloperEntity(developerEntity)
                    .open(copyCurrentState = true)
                    .rootState(RootState.Developer)
                    .commit()
            }
        }
    }

    private fun loginAdmin() {
        if (!validateAdmin()) return

        appStateHandler
            .open(copyCurrentState = true)
            .rootState(RootState.Admin)
            .adminState(AdminState.Users)
            .commit()
    }

    private fun validateUser(): Boolean {

        if (login.length < 6) {
            loginError = "Логін повинен мати більше 5 символів"
            return false
        }

        if (login.isDigitsOnly()) {
            loginError = "Логін повинен мати букви"
            return false
        }

        return true
    }

    private fun validateDeveloper(): Boolean {

        if (login.length < 6) {
            loginError = "Логін повинен мати більше 5 символів"
            return false
        }

        if (login.isDigitsOnly()) {
            loginError = "Логін повинен мати букви"
            return false
        }

        return true
    }

    private fun validateAdmin(): Boolean {

        if (login != "admin" || password != "admin") {
            loginError = "Неправильний логін або пароль"
            passwordError = "Неправильний логін або пароль"
            return false
        }

        return true
    }
}