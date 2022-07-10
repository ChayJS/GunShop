package com.tema.gunshop.program.components.registration

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.RadioButtonWithText
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.inputs.LoginInput
import com.tema.gunshop.system.common.inputs.NameInput
import com.tema.gunshop.system.common.inputs.NumberInput
import com.tema.gunshop.system.common.inputs.PasswordInput
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.text.TitleText
import com.tema.gunshop.system.entity.enums.Role

@Preview
@Composable
fun RegistrationComponent(
    registrationViewModel: RegistrationViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { registrationViewModel.onLaunched() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
                .animateContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            HeightSpacer(8.dp)

            TitleText(text = "Реєстрація")

            HeightSpacer(40.dp)

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                RadioButtonWithText(
                    text = "Клієнт",
                    selected = registrationViewModel.role == Role.User,
                    onClick = {
                        registrationViewModel.onRoleUpdated(Role.User)
                    },
                )

                RadioButtonWithText(
                    text = "Менеджер",
                    selected = registrationViewModel.role == Role.Developer,
                    onClick = {
                        registrationViewModel.onRoleUpdated(Role.Developer)
                    },
                )
            }

            HeightSpacer(4.dp)

            Text(
                text = registrationViewModel.roleError,
                fontSize = 12.sp,
                color = Color.Red,
            )

            HeightSpacer(24.dp)

            if (registrationViewModel.role != null) {

                if (registrationViewModel.role == Role.User) {

                    NameInput(
                        title = "Ім'я",
                        errorText = registrationViewModel.firstNameError,
                        value = registrationViewModel.firstName,
                        onValueChange = registrationViewModel::onFirstNameUpdated,
                    )

                    HeightSpacer(20.dp)

                    NameInput(
                        title = "Прізвище",
                        errorText = registrationViewModel.secondNameError,
                        value = registrationViewModel.secondName,
                        onValueChange = registrationViewModel::onSecondNameUpdated,
                    )

                    HeightSpacer(20.dp)

                    NumberInput(
                        errorText = registrationViewModel.ageError,
                        value = registrationViewModel.age,
                        onValueChange = registrationViewModel::onAgeUpdated,
                    )
                } else if (registrationViewModel.role == Role.Developer) {

                    NameInput(
                        title = "Постачальник",
                        errorText = registrationViewModel.companyNameError,
                        value = registrationViewModel.companyName,
                        onValueChange = registrationViewModel::onCompanyNameUpdated,
                    )
                }

                HeightSpacer(20.dp)

                LoginInput(
                    value = registrationViewModel.login,
                    errorText = registrationViewModel.loginError,
                    onValueChange = registrationViewModel::onLoginUpdated,
                )

                HeightSpacer(20.dp)

                PasswordInput(
                    value = registrationViewModel.password,
                    errorText = registrationViewModel.passwordError,
                    onValueChange = registrationViewModel::onPasswordUpdated,
                )

                HeightSpacer(20.dp)

                PasswordInput(
                    title = "Повторіть пароль",
                    errorText = registrationViewModel.confirmedPasswordError,
                    value = registrationViewModel.confirmedPassword,
                    onValueChange = registrationViewModel::onConfirmedPasswordUpdated,
                )

                HeightSpacer(20.dp)
            }

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Зареєструватись",
                onClick = {
                    registrationViewModel.onRegistrationClicked()
                }
            )

            HeightSpacer(20.dp)
        }
    }

    DisposableEffect(LocalLifecycleOwner.current) { onDispose(registrationViewModel::onDisposed) }
}