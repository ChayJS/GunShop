package com.tema.gunshop.system.common.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier.height(44.dp),
        onClick = onClick,
        enabled = enabled,
        content = {

            Text(
                text = text,
                fontSize = 18.sp,
            )
        }
    )
}