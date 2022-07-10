package com.tema.gunshop.program.components.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.DeveloperDao
import com.tema.gunshop.program.database.dao.UserDao
import com.tema.gunshop.program.database.entity.DeveloperEntity
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.program.components.RootState
import com.tema.gunshop.program.components.user.UserState
import com.tema.gunshop.system.entity.enums.Role
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val userDao: UserDao,
    private val developerDao: DeveloperDao,
) : BaseViewModel(appStateHandler) {

    var firstName by mutableStateOf("")
        private set

    var secondName by mutableStateOf("")
        private set

    var age by mutableStateOf("")
        private set

    var companyName by mutableStateOf("")
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

    var companyNameError by mutableStateOf("")
        private set

    var loginError by mutableStateOf("")
        private set

    var passwordError by mutableStateOf("")
        private set

    var confirmedPasswordError by mutableStateOf("")
        private set

    var roleError by mutableStateOf("")
        private set

    fun onLaunched() {
        role = appData.role
    }

    fun onDisposed() {
        firstName = ""
        secondName = ""
        age = ""
        companyName = ""
        login = ""
        password = ""
        confirmedPassword = ""
        role = null
        firstNameError = ""
        secondNameError = ""
        ageError = ""
        companyNameError = ""
        loginError = ""
        passwordError = ""
        confirmedPasswordError = ""
        roleError = ""
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

    fun onRoleUpdated(role: Role) {
        this.role = role
    }

    fun onRegistrationClicked() {
        if (isLoginCorrect() && isPasswordsCorrect()) {

            launch {

                val appStateBuilder = appStateHandler.open(copyCurrentState = true)

                when (role) {

                    Role.User -> {
                        val userEntity = UserEntity(
                            firstName = firstName,
                            lastName = secondName,
                            login = login,
                            password = password,
                            age = age.trim().toInt(),
                            balance = 0,
                        )
                        userDao.insertAll(userEntity)
                        appStateHandler.saveUserEntity(userDao.getUserByLogin(login))
                        appStateBuilder.rootState(RootState.User).userState(UserState.Profile)
                    }

                    Role.Developer -> {
                        val developerEntity = DeveloperEntity(
                            name = companyName,
                            login = login,
                            password = password,
                        )
                        developerDao.insertAll(developerEntity)
                        appStateHandler.saveDeveloperEntity(developerDao.getDeveloperByLogin(login))
                        appStateBuilder.rootState(RootState.Developer)
                    }

                    else -> {
                        roleError = "Виберіть роль"
                    }
                }

                appStateBuilder.commit()
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