package com.tema.gunshop.program.components.create

import com.tema.gunshop.system.base.BaseViewModel
import com.tema.gunshop.system.navigation.AppStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    appStateHandler: AppStateHandler,
) : BaseViewModel(appStateHandler) {


}