package com.tema.gunshop.system.common.usages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NotImplemented() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Not implemented",
        )
    }
}