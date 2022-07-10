package com.tema.gunshop.program.components.admin.games

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
import com.tema.gunshop.system.entity.enums.GameSortBy

@Composable
fun GamesComponent(
    gamesViewModel: GamesViewModel = viewModel(),
) {
    LaunchedEffect(Unit) { gamesViewModel.onLaunched() }

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
                selected = gamesViewModel.gameSortBy == GameSortBy.Id,
                onClick = {
                    gamesViewModel.onSortClicked(GameSortBy.Id)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Назва",
                selected = gamesViewModel.gameSortBy == GameSortBy.Name,
                onClick = {
                    gamesViewModel.onSortClicked(GameSortBy.Name)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Постачальник",
                selected = gamesViewModel.gameSortBy == GameSortBy.Developer,
                onClick = {
                    gamesViewModel.onSortClicked(GameSortBy.Developer)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Клас",
                selected = gamesViewModel.gameSortBy == GameSortBy.Genre,
                onClick = {
                    gamesViewModel.onSortClicked(GameSortBy.Genre)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Мінімальний вік",
                selected = gamesViewModel.gameSortBy == GameSortBy.MinAge,
                onClick = {
                    gamesViewModel.onSortClicked(GameSortBy.MinAge)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Ціна",
                selected = gamesViewModel.gameSortBy == GameSortBy.Price,
                onClick = {
                    gamesViewModel.onSortClicked(GameSortBy.Price)
                },
            )

            WidthSpacer(4.dp)

            RadioButtonWithText(
                text = "Вогнева міць",
                selected = gamesViewModel.gameSortBy == GameSortBy.Rating,
                onClick = {
                    gamesViewModel.onSortClicked(GameSortBy.Rating)
                },
            )

            WidthSpacer(8.dp)
        }

        WidthSpacer(16.dp)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {

            gamesViewModel.games.forEach { game ->

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
                                text = "ID: ${game.id}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Назва: ${game.name}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Постачальник: ${game.developerName}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Клас: ${game.genre}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Мінімальній вік: ${game.minAge}",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Ціна: ${game.price} грн.",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(6.dp)

                            Text(
                                text = "Вогнева міць: ${game.rating}/10",
                                fontWeight = FontWeight.Medium,
                            )

                            HeightSpacer(8.dp)

                            TextButton(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Видалити",
                                onClick = {
                                    gamesViewModel.onDeleteGameClicked(game)
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