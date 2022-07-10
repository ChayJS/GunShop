package com.tema.gunshop.program.components.create.user

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
import com.tema.gunshop.system.common.inputs.NumberInput
import com.tema.gunshop.system.common.inputs.PasswordInput
import com.tema.gunshop.system.common.spacer.HeightSpacer

@Composable
fun CreateUserComponent(
    createUserViewModel: CreateUserViewModel = viewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        NameInput(
            title = "Ім'я",
            errorText = createUserViewModel.firstNameError,
            value = createUserViewModel.firstName,
            onValueChange = createUserViewModel::onFirstNameUpdated,
        )

        HeightSpacer(20.dp)

        NameInput(
            title = "Прізвище",
            errorText = createUserViewModel.secondNameError,
            value = createUserViewModel.secondName,
            onValueChange = createUserViewModel::onSecondNameUpdated,
        )

        HeightSpacer(20.dp)

        NumberInput(
            errorText = createUserViewModel.ageError,
            value = createUserViewModel.age,
            onValueChange = createUserViewModel::onAgeUpdated,
        )

        HeightSpacer(20.dp)

        LoginInput(
            value = createUserViewModel.login,
            errorText = createUserViewModel.loginError,
            onValueChange = createUserViewModel::onLoginUpdated,
        )

        HeightSpacer(20.dp)

        PasswordInput(
            value = createUserViewModel.password,
            errorText = createUserViewModel.passwordError,
            onValueChange = createUserViewModel::onPasswordUpdated,
        )

        HeightSpacer(20.dp)

        PasswordInput(
            title = "Повторити пароль",
            errorText = createUserViewModel.confirmedPasswordError,
            value = createUserViewModel.confirmedPassword,
            onValueChange = createUserViewModel::onConfirmedPasswordUpdated,
        )

        HeightSpacer(20.dp)

        TextButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Створити",
            onClick = createUserViewModel::onCreateUserClicked,
        )

        HeightSpacer(20.dp)
    }

    DisposableEffect(Unit) { onDispose(createUserViewModel::onDisposed) }
}