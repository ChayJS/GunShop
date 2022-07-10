package com.tema.gunshop.system.base

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tema.gunshop.system.navigation.AppStateHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    protected val appStateHandler: AppStateHandler,
) : ViewModel(), CoroutineScope {

    val appState by appStateHandler.appStateState
    val appData by appStateHandler.appDataState

    override val coroutineContext: CoroutineContext =
        viewModelScope.coroutineContext + Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            onCoroutineException(throwable)
        }

    protected open fun onCoroutineException(throwable: Throwable) {
        Log.e("COROUTINE_EXCEPTION", throwable.stackTraceToString())
    }
}