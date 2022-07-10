package com.tema.gunshop.program.components.user.mygames

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.spacer.HeightSpacer

@Composable
fun MyGamesComponent(
    myGamesViewModel: MyGamesViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { myGamesViewModel.onLaunched() }

    LazyColumn {

        myGamesViewModel.games.forEach { game ->

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
                            text = "Мінімальний вік: ${game.minAge}",
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
                    }
                }

                HeightSpacer(8.dp)
            }
        }
    }
}