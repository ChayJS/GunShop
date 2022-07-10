package com.tema.gunshop.program.components.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.inputs.LoginInput
import com.tema.gunshop.system.common.inputs.PasswordInput
import com.tema.gunshop.system.common.modifier.onClickWithoutIndication
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.text.TitleText
import com.tema.gunshop.system.entity.enums.Role

@Preview
@Composable
fun LoginComponent(
    loginViewModel: LoginViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { loginViewModel.onLaunched() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TitleText(text = loginViewModel.appData.role.toString())

            HeightSpacer(40.dp)

            LoginInput(
                value = loginViewModel.login,
                errorText = loginViewModel.loginError,
                onValueChange = loginViewModel::onLoginUpdated,
            )

            HeightSpacer(20.dp)

            PasswordInput(
                value = loginViewModel.password,
                errorText = loginViewModel.passwordError,
                onValueChange = loginViewModel::onPasswordUpdated,
            )

            HeightSpacer(40.dp)

            TextButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Увійти",
                onClick = loginViewModel::onLoginClicked,
            )
        }

        if (loginViewModel.appData.role != Role.Admin) {

            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .onClickWithoutIndication(loginViewModel::onMoveToRegistrationClicked),
                text = "Немає акаунта?",
            )
        }
    }

    DisposableEffect(LocalLifecycleOwner.current) { onDispose(loginViewModel::onDisposed) }
}