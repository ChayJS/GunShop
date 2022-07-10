package com.tema.gunshop.system.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.tema.gunshop.program.database.entity.DeveloperEntity
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.program.components.RootState
import com.tema.gunshop.program.components.admin.AdminState
import com.tema.gunshop.program.components.create.CreateState
import com.tema.gunshop.program.components.user.UserState
import com.tema.gunshop.system.entity.enums.Role
import java.util.*

class AppStateHandler {

    private val appStatesStack = Stack<AppState>()

    private val appStateMutableState = mutableStateOf(AppState())
    val appStateState: State<AppState> = appStateMutableState

    private val appDataMutableState = mutableStateOf(AppData())
    val appDataState: State<AppData> = appDataMutableState

    fun saveRole(value: Role?) = this.apply {
        appDataMutableState.value = appDataState.value.copy(role = value)
    }

    fun saveUserEntity(value: UserEntity?) = this.apply {
        appDataMutableState.value = appDataState.value.copy(userEntity = value)
    }

    fun saveDeveloperEntity(value: DeveloperEntity?) = this.apply {
        appDataMutableState.value = appDataState.value.copy(developerEntity = value)
    }

    fun open(copyCurrentState: Boolean) = AppStateBuilder(
        appState = if (copyCurrentState) appStateState.value.copy() else AppState(),
        onCommitted = { newAppState ->
            appStateMutableState.value = newAppState
            appStatesStack.push(newAppState)
        },
    )

    fun replace(copyCurrentState: Boolean) = AppStateBuilder(
        appState = if (copyCurrentState) appStateState.value.copy() else AppState(),
        onCommitted = { newAppState ->
            if (appStatesStack.isNotEmpty()) {
                appStatesStack.pop()
            }
            appStateMutableState.value = newAppState
            appStatesStack.push(newAppState)
        },
    )

    fun back(onAppStatesStackEmpty: (() -> Unit)? = null) {
        if (appStatesStack.size > 1) {
            appStatesStack.pop()
            appStateMutableState.value = appStatesStack.peek()
        } else {
            onAppStatesStackEmpty?.let { it() }
        }
    }

    fun clearBackStack() = this.apply {
        appStatesStack.clear()
    }

    class AppStateBuilder(
        private var appState: AppState,
        private val onCommitted: (AppState) -> Unit,
    ) {

        fun rootState(value: RootState) = this.apply {
            appState = appState.copy(rootState = value)
        }

        fun adminState(value: AdminState) = this.apply {
            appState = appState.copy(adminState = value)
        }

        fun userState(value: UserState) = this.apply {
            appState = appState.copy(userState = value)
        }

        fun createState(value: CreateState) = this.apply {
            appState = appState.copy(createState = value)
        }

        fun commit() = onCommitted(appState)
    }
}