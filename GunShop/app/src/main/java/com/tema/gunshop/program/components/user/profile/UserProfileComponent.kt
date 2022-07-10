package com.tema.gunshop.program.components.user.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.inputs.NameInput
import com.tema.gunshop.system.common.inputs.NumberInput
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.text.SubtitleText
import com.tema.gunshop.system.common.text.TitleText

@Composable
fun UserProfileComponent(
    userProfileViewModel: UserProfileViewModel = viewModel(),
) {
    val userEntity = userProfileViewModel.appData.userEntity
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        userProfileViewModel.onLaunched()
        userProfileViewModel.onContextAttached(context)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        HeightSpacer(12.dp)

        SubtitleText(
            text = "Логін: ${userEntity?.login}",
        )

        HeightSpacer(12.dp)

        NameInput(
            title = "Ім'я",
            errorText = userProfileViewModel.firstNameError,
            value = userProfileViewModel.firstName,
            onValueChange = userProfileViewModel::onFirstNameUpdated,
        )

        HeightSpacer(12.dp)

        NameInput(
            title = "Прізвище",
            errorText = userProfileViewModel.secondNamError,
            value = userProfileViewModel.secondName,
            onValueChange = userProfileViewModel::onSecondNameUpdated,
        )

        HeightSpacer(12.dp)

        NumberInput(
            title = "Вік",
            errorText = userProfileViewModel.ageError,
            value = userProfileViewModel.age,
            onValueChange = userProfileViewModel::onAgeUpdated,
        )

        HeightSpacer(12.dp)

        TextButton(
            text = "Зберегти",
            onClick = userProfileViewModel::onSaveButtonClicked,
        )

        HeightSpacer(20.dp)

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )

        HeightSpacer(20.dp)

        TitleText(
            text = "Рахунок: ${userEntity?.balance} грн.",
        )

        HeightSpacer(20.dp)

        NumberInput(
            title = "Пополнити на",
            errorText = userProfileViewModel.moneyError,
            value = userProfileViewModel.money,
            onValueChange = userProfileViewModel::onMoneyUpdated,
        )

        HeightSpacer(12.dp)

        TextButton(
            text = "Поповнити",
            onClick = userProfileViewModel::onAddToBalanceClicked,
        )

        HeightSpacer(20.dp)
    }

    DisposableEffect(Unit) { onDispose(userProfileViewModel::onContextDetached) }
}