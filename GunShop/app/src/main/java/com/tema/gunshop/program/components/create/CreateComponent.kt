package com.tema.gunshop.program.components.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.text.TitleText
import com.tema.gunshop.system.common.usages.NotImplemented
import com.tema.gunshop.program.components.create.developer.CreateDeveloperComponent
import com.tema.gunshop.program.components.create.game.CreateGameComponent
import com.tema.gunshop.program.components.create.user.CreateUserComponent

@Composable
fun CreateComponent(
    createViewModel: CreateViewModel = viewModel(),
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        HeightSpacer(8.dp)

        when (createViewModel.appState.createState) {

            CreateState.CreateUser -> {

                TitleText(text = "Створити клієнта")

                HeightSpacer(12.dp)

                CreateUserComponent()
            }

            CreateState.CreateDeveloper -> {

                TitleText(text = "Створити постачальника")

                HeightSpacer(12.dp)

                CreateDeveloperComponent()
            }

            CreateState.CreateGame -> {

                TitleText(text = "Створити зброю")

                HeightSpacer(12.dp)

                CreateGameComponent()
            }

            else -> NotImplemented()
        }
    }
}