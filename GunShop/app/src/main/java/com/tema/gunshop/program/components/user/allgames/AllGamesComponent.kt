package com.tema.gunshop.program.components.user.allgames

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.buttons.TextButton
import com.tema.gunshop.system.common.spacer.HeightSpacer

@Composable
fun AllGamesComponent(
    allGamesViewModel: AllGamesViewModel = viewModel(),
) {
    val userEntity = allGamesViewModel.appData.userEntity!!
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        allGamesViewModel.onLaunched()
        allGamesViewModel.onContextAttached(context)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {

        allGamesViewModel.games.forEach { game ->

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

                        HeightSpacer(8.dp)

                        val gameBought = allGamesViewModel.myGames.contains(game)
                        val minAgeMatch = userEntity.age >= game.minAge
                        val enoughMoney = userEntity.balance >= game.price

                        TextButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = when {
                                gameBought -> "Куплено"
                                !minAgeMatch -> "Ви занадто малі"
                                !enoughMoney -> "Немає грошей"
                                else -> "Купити"
                            },
                            enabled = !gameBought && minAgeMatch && enoughMoney,
                            onClick = { allGamesViewModel.onBuyGameClicked(game) },
                        )
                    }
                }

                HeightSpacer(8.dp)
            }
        }
    }

    DisposableEffect(Unit) { onDispose(allGamesViewModel::onContextDetached) }
}