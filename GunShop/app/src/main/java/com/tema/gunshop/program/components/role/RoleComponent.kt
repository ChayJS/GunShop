package com.tema.gunshop.program.components.role

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.modifier.onClickWithoutIndication
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.text.TitleText

@Composable
fun RoleComponent(
    roleViewModel: RoleViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { roleViewModel.onLaunched() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TitleText(text = "Увійти як")

            HeightSpacer(40.dp)

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = roleViewModel::onEnterAsUserClicked,
                text = "Клієнт",
            )

            HeightSpacer(20.dp)

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = roleViewModel::onEnterAsDeveloperClicked,
                text = "Менеджер",
            )

            HeightSpacer(20.dp)

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                onClick = roleViewModel::onEnterAsAdminClicked,
                text = "Адміністратор",
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
                .onClickWithoutIndication(roleViewModel::onMoveToRegistrationClicked),
            text = "Немає акаунта?",
        )
    }
}