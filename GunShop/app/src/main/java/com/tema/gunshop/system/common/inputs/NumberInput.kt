package com.tema.gunshop.system.common.inputs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tema.gunshop.system.common.modifier.onClickWithoutIndication
import com.tema.gunshop.system.common.spacer.HeightSpacer

@Composable
fun NumberInput(
    modifier: Modifier = Modifier,
    title: String = "Возраст",
    errorText: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .onClickWithoutIndication(focusRequester::requestFocus),
    ) {

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = value,
            label = {
                Text(text = title)
            },
            isError = errorText.isNotEmpty(),
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                errorCursorColor = Color.Red,
                errorIndicatorColor = Color.Red,
                errorLabelColor = Color.Red,
            ),
        )

        HeightSpacer(4.dp)

        Text(
            text = errorText,
            fontSize = 12.sp,
            color = Color.Red,
        )
    }
}