package com.tema.gunshop.program.components.admin.developers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.tema.gunshop.program.database.dao.DeveloperDao
import com.tema.gunshop.program.database.entity.DeveloperEntity
import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.entity.enums.DeveloperSortBy
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ManagersViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
    private val developerDao: DeveloperDao,
) : BaseViewModel(appStateHandler) {

    var developers by mutableStateOf<List<DeveloperEntity>>(emptyList())
        private set

    var developerSortBy by mutableStateOf(DeveloperSortBy.Id)
        private set

    fun onLaunched() {
        launch {
            developers = developerDao.getAll()
        }
    }

    fun onSortClicked(developerSortBy: DeveloperSortBy) {
        this.developerSortBy = developerSortBy

        developers = when (developerSortBy) {

            DeveloperSortBy.Id -> developers.sortedBy { it.id }
            DeveloperSortBy.Name -> developers.sortedBy { it.name }
            DeveloperSortBy.Login -> developers.sortedBy { it.login }
        }
    }

    fun onDeleteDeveloperClicked(developerEntity: DeveloperEntity) {
        launch {
            developerDao.delete(developerEntity)

            developers = mutableListOf<DeveloperEntity>().apply {
                addAll(developers)
                remove(developerEntity)
            }
        }
    }
}