package com.tema.gunshop.program.components.admin.developers

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
import com.tema.gunshop.system.entity.enums.DeveloperSortBy

@Composable
fun ManagerComponent(
    managersViewModel: ManagersViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { managersViewModel.onLaunched() }

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
                selected = managersViewModel.developerSortBy == DeveloperSortBy.Id,
                onClick = {
                    managersViewModel.onSortClicked(DeveloperSortBy.Id)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Постачальник",
                selected = managersViewModel.developerSortBy == DeveloperSortBy.Name,
                onClick = {
                    managersViewModel.onSortClicked(DeveloperSortBy.Name)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Логін",
                selected = managersViewModel.developerSortBy == DeveloperSortBy.Login,
                onClick = {
                    managersViewModel.onSortClicked(DeveloperSortBy.Login)
                },
            )

            WidthSpacer(8.dp)
        }

        WidthSpacer(16.dp)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            managersViewModel.developers.forEach { developerEntity ->

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
                                text = "ID: ${developerEntity.id}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Постачальник: ${developerEntity.name}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Логін: ${developerEntity.login}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(8.dp)

                            TextButton(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Видалити",
                                onClick = {
                                    managersViewModel.onDeleteDeveloperClicked(developerEntity)
                                },
                            )
                        }
                    }

                    HeightSpacer(8.dp)
                }
            }
        }
    }
}