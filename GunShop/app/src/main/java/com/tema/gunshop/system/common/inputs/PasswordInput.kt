package com.tema.gunshop.system.common.inputs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tema.gunshop.system.common.spacer.HeightSpacer

@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    title: String = "Пароль",
    errorText: String,
    value: String,
    onValueChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    var hidePassword by rememberSaveable { mutableStateOf(true) }

    Column(
        modifier = modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {
                focusRequester.requestFocus()
            },
        ),
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
                keyboardType = KeyboardType.Password,
            ),
            visualTransformation = if (hidePassword) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            trailingIcon = {
                IconButton(
                    onClick = { hidePassword = !hidePassword },
                    content = {
                        Icon(
                            imageVector = if (hidePassword) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = null,
                        )
                    },
                )
            },
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