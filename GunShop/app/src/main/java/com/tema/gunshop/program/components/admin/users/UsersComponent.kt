package com.tema.gunshop.program.components.admin.users

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.RadioButtonWithText
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.spacer.WidthSpacer
import com.tema.gunshop.system.entity.enums.UserSortBy

@Composable
fun UsersComponent(
    usersViewModel: UsersViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { usersViewModel.onLaunched() }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState()),
        ) {

            RadioButtonWithText(
                text = "ID",
                selected = usersViewModel.userSortBy == UserSortBy.Id,
                onClick = {
                    usersViewModel.onSortClicked(UserSortBy.Id)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Ім'я",
                selected = usersViewModel.userSortBy == UserSortBy.FirstName,
                onClick = {
                    usersViewModel.onSortClicked(UserSortBy.FirstName)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Прізвище",
                selected = usersViewModel.userSortBy == UserSortBy.LastName,
                onClick = {
                    usersViewModel.onSortClicked(UserSortBy.LastName)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Логін",
                selected = usersViewModel.userSortBy == UserSortBy.Login,
                onClick = {
                    usersViewModel.onSortClicked(UserSortBy.Login)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Вік",
                selected = usersViewModel.userSortBy == UserSortBy.Age,
                onClick = {
                    usersViewModel.onSortClicked(UserSortBy.Age)
                },
            )

            WidthSpacer(8.dp)
        }

        WidthSpacer(16.dp)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            usersViewModel.users.forEach { userEntity ->

                item {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        backgroundColor = Color.LightGray,
                        border = BorderStroke(
                            width = 2.dp,
                            color = Color.DarkGray,
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 8.dp)
                        ) {

                            HeightSpacer(6.dp)

                            Text(
                                text = "ID: ${userEntity.id}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Ім'я: ${userEntity.firstName}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Прізвище: ${userEntity.lastName}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Логін: ${userEntity.login}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Вік: ${userEntity.age}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Рахунок: ${userEntity.balance} грн.",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(8.dp)

                            TextButton(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Видалити",
                                onClick = { usersViewModel.onDeleteUserClicked(userEntity) },
                            )
                        }
                    }

                    HeightSpacer(8.dp)
                }
            }
        }
    }
}



