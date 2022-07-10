package com.tema.gunshop.program.components.create.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.inputs.NameInput
import com.tema.gunshop.system.common.inputs.NumberInput
import com.tema.gunshop.system.common.spacer.HeightSpacer

@Composable
fun CreateGameComponent(
    createGameViewModel: CreateGameViewModel = viewModel(),
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        HeightSpacer(20.dp)

        NameInput(
            title = "Назва зброї",
            errorText = createGameViewModel.nameError,
            value = createGameViewModel.name,
            onValueChange = createGameViewModel::onNameUpdated,
        )

        HeightSpacer(20.dp)

        NameInput(
            title = "Клас",
            errorText = createGameViewModel.genreError,
            value = createGameViewModel.genre,
            onValueChange = createGameViewModel::onGenreUpdated,
        )

        HeightSpacer(20.dp)

        NumberInput(
            title = "Мінімальний вік",
            errorText = createGameViewModel.minAgeError,
            value = createGameViewModel.minAge,
            onValueChange = createGameViewModel::onMinAgeUpdated,
        )

        HeightSpacer(20.dp)

        NumberInput(
            title = "Ціна",
            errorText = createGameViewModel.priceError,
            value = createGameViewModel.price,
            onValueChange = createGameViewModel::onPriceUpdated,
        )

        HeightSpacer(20.dp)

        TextButton(
            text = "Створити",
            onClick = createGameViewModel::onCreateClicked,
        )

        HeightSpacer(20.dp)
    }
}