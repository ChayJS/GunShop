package com.tema.gunshop.program.components.create.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.UserDao
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.entity.enums.Role
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateUserViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val userDao: UserDao,
) : BaseViewModel(appStateHandler) {

    var firstName by mutableStateOf("")
        private set

    var secondName by mutableStateOf("")
        private set

    var age by mutableStateOf("")
        private set

    var login by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var confirmedPassword by mutableStateOf("")
        private set

    var role by mutableStateOf<Role?>(null)
        private set

    var firstNameError by mutableStateOf("")
        private set

    var secondNameError by mutableStateOf("")
        private set

    var ageError by mutableStateOf("")
        private set

    var loginError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var confirmedPasswordError by mutableStateOf("")
        private set

    fun onLaunched() {
        role = appData.role
    }

    fun onDisposed() {
        firstName = ""
        secondName = ""
        age = ""
        login = ""
        password = ""
        confirmedPassword = ""
        firstNameError = ""
        secondNameError = ""
        ageError = ""
        loginError = ""
        passwordError = ""
        confirmedPasswordError = ""
    }

    fun onFirstNameUpdated(firstName: String) {
        this.firstName = firstName
        firstNameError = ""
    }

    fun onSecondNameUpdated(secondName: String) {
        this.secondName = secondName
        secondNameError = ""
    }

    fun onAgeUpdated(age: String) {
        this.age = age
        ageError = ""
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

    fun onCreateUserClicked() {

        launch {

            if (isLoginCorrect() && isPasswordsCorrect()) {

                val userEntity = UserEntity(
                    firstName = firstName,
                    lastName = secondName,
                    login = login,
                    password = password,
                    age = age.trim().toInt(),
                    balance = 0,
                )

                userDao.insertAll(userEntity)

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