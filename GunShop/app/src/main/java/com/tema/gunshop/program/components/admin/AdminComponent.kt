package com.tema.gunshop.program.components.admin

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.usages.NotImplemented
import com.tema.gunshop.program.components.admin.developers.ManagerComponent
import com.tema.gunshop.program.components.admin.games.GamesComponent
import com.tema.gunshop.program.components.admin.users.UsersComponent

@Composable
fun AdminComponent(
    adminViewModel: AdminViewModel = viewModel(),
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues = paddingValues),
            ) {

                when (adminViewModel.appState.adminState) {

                    is AdminState.Users -> UsersComponent()

                    is AdminState.Managers -> ManagerComponent()

                    is AdminState.Games -> GamesComponent()

                    else -> NotImplemented()
                }
            }
        },
        floatingActionButton = {
            if (adminViewModel.appState.adminState != AdminState.Games) {

                FloatingActionButton(
                    onClick = adminViewModel::onCreateClicked,
                    content = {
                        Text(
                            modifier = Modifier.padding(bottom = 2.dp),
                            text = "+",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 35.sp,
                        )
                    },
                )
            }
        },
        bottomBar = {
            BottomNavigation(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {

                BottomNavigationItem(
                    selected = adminViewModel.appState.adminState == AdminState.Users,
                    onClick = adminViewModel::onUsersSelected,
                    label = {
                        Text(text = "Клієнти")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.VerifiedUser,
                            contentDescription = null,
                        )
                    }
                )

                BottomNavigationItem(
                    selected = adminViewModel.appState.adminState == AdminState.Managers,
                    onClick = adminViewModel::onDevelopersSelected,
                    label = {
                        Text(text = "Менеджери")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.VerifiedUser,
                            contentDescription = null,
                        )
                    }
                )

                BottomNavigationItem(
                    selected = adminViewModel.appState.adminState == AdminState.Games,
                    onClick = adminViewModel::onGamesSelected,
                    label = {
                        Text(text = "Зброя")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.VerifiedUser,
                            contentDescription = null,
                        )
                    }
                )
            }
        },
    )
}


