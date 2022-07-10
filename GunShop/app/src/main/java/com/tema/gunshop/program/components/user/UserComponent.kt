package com.tema.gunshop.program.components.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.usages.NotImplemented
import com.tema.gunshop.program.components.user.allgames.AllGamesComponent
import com.tema.gunshop.program.components.user.mygames.MyGamesComponent
import com.tema.gunshop.program.components.user.profile.UserProfileComponent
import com.tema.gunshop.program.components.user.statistic.StatisticComponent

@Preview
@Composable
fun UserComponent(
    userViewModel: UserViewModel = viewModel(),
) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {

                when (userViewModel.appState.userState) {

                    is UserState.Profile -> UserProfileComponent()

                    is UserState.MyGames -> MyGamesComponent()

                    is UserState.AllGames -> AllGamesComponent()

                    is UserState.Statistic -> StatisticComponent()

                    else -> NotImplemented()

                }
            }
        },
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.fillMaxWidth(),
            ) {

                BottomNavigationItem(
                    selected = userViewModel.appState.userState == UserState.Profile,
                    onClick = userViewModel::onProfileSelected,
                    label = {
                        Text(text = "Профіль")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.VerifiedUser,
                            contentDescription = null,
                        )
                    }
                )

                BottomNavigationItem(
                    selected = userViewModel.appState.userState == UserState.MyGames,
                    onClick = userViewModel::onMyGamesSelected,
                    label = {
                        Text(text = "Моя зброя")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.VerifiedUser,
                            contentDescription = null,
                        )
                    }
                )

                BottomNavigationItem(
                    selected = userViewModel.appState.userState == UserState.AllGames,
                    onClick = userViewModel::onAllGamesSelected,
                    label = {
                        Text(text = "Весь асортимент")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.VerifiedUser,
                            contentDescription = null,
                        )
                    }
                )

                BottomNavigationItem(
                    selected = userViewModel.appState.userState == UserState.Statistic,
                    onClick = userViewModel::onStatisticSelected,
                    label = {
                        Text(text = "Статистика")
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