package com.tema.gunshop.program.components.user.statistic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.program.database.entity.GunEntity
import com.tema.gunshop.program.database.entity.UserEntity
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.text.TitleText

@Composable
fun StatisticComponent(
    statisticViewModel: StatisticViewModel = viewModel(),
) {

    LaunchedEffect(Unit) {
        statisticViewModel.onLaunched()
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {

        item {

            HeightSpacer(12.dp)

            TitleText(text = "Топ 5 зброї по покупкам")

            HeightSpacer(20.dp)
        }

        gameItems(statisticViewModel.theMostPopularGames)

        item {

            HeightSpacer(12.dp)

            TitleText(text = "Топ 5 клієнтів по кількості покупок")

            HeightSpacer(12.dp)
        }

        userItems(statisticViewModel.usersWithTheBiggestGamesCount)

        item {

            HeightSpacer(12.dp)

            TitleText(text = "Топ 5 найдорощої зброї")

            HeightSpacer(12.dp)
        }

        gameItems(statisticViewModel.theMostExpensiveGames)
    }
}

private fun LazyListScope.gameItems(games: List<GunEntity>) {

    games.forEach { gameEntity ->

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

                    Text(
                        text = "ID: ${gameEntity.id}",
                        fontWeight = FontWeight.Medium,
                    )

                    HeightSpacer(6.dp)

                    Text(
                        text = "Назва: ${gameEntity.name}",
                        fontWeight = FontWeight.Medium,
                    )

                    HeightSpacer(6.dp)

                    Text(
                        text = "Клас: ${gameEntity.genre}",
                        fontWeight = FontWeight.Medium,
                    )

                    HeightSpacer(6.dp)

                    Text(
                        text = "Мінімальний вік: ${gameEntity.minAge}",
                        fontWeight = FontWeight.Medium,
                    )

                    HeightSpacer(6.dp)

                    Text(
                        text = "Ціна: ${gameEntity.price} грн.",
                        fontWeight = FontWeight.Medium,
                    )

                    HeightSpacer(6.dp)

                    Text(
                        text = "Вогнева міць: ${gameEntity.rating}/10",
                        fontWeight = FontWeight.Medium,
                    )

                    HeightSpacer(8.dp)
                }
            }

            HeightSpacer(8.dp)
        }
    }
}

private fun LazyListScope.userItems(users: List<UserEntity>) {

    users.forEach { userEntity ->

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

                    HeightSpacer(8.dp)
                }
            }

            HeightSpacer(8.dp)
        }
    }
}