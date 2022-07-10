package com.tema.gunshop.program.components.create.developer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.inputs.LoginInput
import com.tema.gunshop.system.common.inputs.NameInput
import com.tema.gunshop.system.common.inputs.PasswordInput
import com.tema.gunshop.system.common.spacer.HeightSpacer

@Composable
fun CreateDeveloperComponent(
    createDeveloperViewModel: CreateDeveloperViewModel = viewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        NameInput(
            title = "Постачальник",
            errorText = createDeveloperViewModel.companyNameError,
            value = createDeveloperViewModel.companyName,
            onValueChange = createDeveloperViewModel::onCompanyNameUpdated,
        )

        HeightSpacer(20.dp)

        LoginInput(
            value = createDeveloperViewModel.login,
            errorText = createDeveloperViewModel.loginError,
            onValueChange = createDeveloperViewModel::onLoginUpdated,
        )

        HeightSpacer(20.dp)

        PasswordInput(
            value = createDeveloperViewModel.password,
            errorText = createDeveloperViewModel.passwordError,
            onValueChange = createDeveloperViewModel::onPasswordUpdated,
        )

        HeightSpacer(20.dp)

        PasswordInput(
            title = "Повторити пароль",
            errorText = createDeveloperViewModel.confirmedPasswordError,
            value = createDeveloperViewModel.confirmedPassword,
            onValueChange = createDeveloperViewModel::onConfirmedPasswordUpdated,
        )

        HeightSpacer(20.dp)

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Створити",
            onClick = createDeveloperViewModel::onCreateDeveloperClicked,
        )

        HeightSpacer(20.dp)
    }

    DisposableEffect(Unit) { onDispose(createDeveloperViewModel::onDisposed) }
}