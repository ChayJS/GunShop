package com.tema.gunshop.program.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tema.gunshop.system.common.usages.NotImplemented
import com.tema.gunshop.program.components.admin.AdminComponent
import com.tema.gunshop.program.components.create.CreateComponent
import com.tema.gunshop.program.components.developer.DeveloperComponent
import com.tema.gunshop.program.components.login.LoginComponent
import com.tema.gunshop.program.components.registration.RegistrationComponent
import com.tema.gunshop.program.components.role.RoleComponent
import com.tema.gunshop.program.components.user.UserComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : ComponentActivity() {

    private val rootViewModel: RootViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            MaterialTheme {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    when (rootViewModel.appState.rootState) {

                        is RootState.Role -> RoleComponent()

                        is RootState.Login -> LoginComponent()

                        is RootState.Registration -> RegistrationComponent()

                        is RootState.Admin -> AdminComponent()

                        is RootState.User -> UserComponent()

                        is RootState.Developer -> DeveloperComponent()

                        is RootState.Create -> CreateComponent()

                        else -> NotImplemented()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        rootViewModel.onBackClicked(::finish)
    }
}