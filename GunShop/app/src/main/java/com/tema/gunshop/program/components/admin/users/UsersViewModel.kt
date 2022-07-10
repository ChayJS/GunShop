package com.tema.gunshop.program.components.admin.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.UserDao
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.entity.enums.UserSortBy
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val userDao: UserDao,
) : BaseViewModel(appStateHandler) {

    var users by mutableStateOf<List<UserEntity>>(emptyList())
        private set

    var userSortBy by mutableStateOf(UserSortBy.Id)
        private set

    fun onLaunched() {
        launch {
            users = userDao.getAll().sortedBy { it.id }
        }
    }

    fun onSortClicked(userSortBy: UserSortBy) {
        this.userSortBy = userSortBy

        users = when (userSortBy) {

            UserSortBy.Id -> users.sortedBy { it.id }
            UserSortBy.FirstName -> users.sortedBy { it.firstName }
            UserSortBy.LastName -> users.sortedBy { it.lastName }
            UserSortBy.Login -> users.sortedBy { it.login }
            UserSortBy.Age -> users.sortedBy { it.age }
        }
    }

    fun onDeleteUserClicked(userEntity: UserEntity) {
        launch {
            userDao.delete(userEntity)

            users = mutableListOf<UserEntity>().apply {
                addAll(users)
                remove(userEntity)
            }
        }
    }
}