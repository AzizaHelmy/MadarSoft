package com.example.madarsoft.presentation.screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType

/**
 * Created by Aziza Helmy on 28/06/2025.
 */


@Composable
fun AppTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    maxLength: Int = 40,
) {
    val containerColor = Color.Transparent
    val borderColor = Color.Gray
    val textColor = Color.Black
    val errorColor = Color.Red

    OutlinedTextField(
        value = text.trim(),
        onValueChange = { newValue ->
            if (keyboardType == KeyboardType.Number) {
                val filtered = newValue.filter { it.isDigit() }
                val numeric = filtered.toIntOrNull()
                if (numeric != null && numeric <= 120) {
                    onValueChange(filtered)
                } else if (numeric == null) {
                    onValueChange("")
                }
            } else {
                if (newValue.length <= maxLength) {
                    onValueChange(newValue)
                }
            }
        },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        singleLine = true,
        leadingIcon = leadingIcon?.let {
            {
                Icon(
                    painter = it,
                    contentDescription = null,
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,

            focusedIndicatorColor = borderColor,
            unfocusedIndicatorColor = borderColor,
            disabledIndicatorColor = borderColor,

            errorIndicatorColor = errorColor,
            errorCursorColor = errorColor,
            errorTextColor = errorColor,

            cursorColor = textColor,
            disabledTextColor = textColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
        ),
        label = {
            if (hint.isNotEmpty()) {
                Text(
                    text = hint,
                    color = Color.Gray,
                )
            }
        },
        shape = MaterialTheme.shapes.small
    )
}
