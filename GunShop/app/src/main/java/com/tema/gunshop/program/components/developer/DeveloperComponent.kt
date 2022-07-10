package com.tema.gunshop.program.components.developer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tema.gunshop.system.common.spacer.HeightSpacer
import com.tema.gunshop.system.common.text.TitleText

@Composable
fun DeveloperComponent(
    developerViewModel: DeveloperViewModel = viewModel(),
) {

    LaunchedEffect(Unit) { developerViewModel.onLaunched() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = developerViewModel::onCreateGameClicked,
                content = {
                    Text(
                        modifier = Modifier.padding(bottom = 2.dp),
                        text = "+",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 35.sp,
                    )
                },
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {

                item {

                    HeightSpacer(8.dp)

                    TitleText(text = developerViewModel.appData.developerEntity!!.name)

                    HeightSpacer(8.dp)
                }

                developerViewModel.developerGames.forEach { gameEntity ->

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
                                    text = "Мінімальній вік: ${gameEntity.minAge}",
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
                    }
                }
            }
        },
    )
}