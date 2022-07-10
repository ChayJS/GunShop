package com.tema.gunshop.system.common.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tema.gunshop.system.common.spacer.WidthSpacer

@Composable
fun RadioButtonWithText(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    onClick: (() -> Unit)?,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        RadioButton(
            selected = selected,
            onClick = onClick,
        )

        WidthSpacer(4.dp)

        Text(text = text)
    }
}