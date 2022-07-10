package com.tema.gunshop.system.common.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SubtitleText(
    modifier: Modifier = Modifier,
    text: String,
) {

    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
    )
}