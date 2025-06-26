package com.example.madarsoft.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(uiState = state.user, onNameChanged =viewModel::onNameChanged)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(uiState: UserUiState, onNameChanged: (String) -> Unit = {}) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Add User") },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.Gray)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AppTextField(
                    modifier = Modifier.padding(top = 16.dp),
                    text = uiState.name,
                    hint = "Name",
                    onValueChange = onNameChanged)
                AppTextField(text = uiState.name, hint = "Title", onValueChange = { })
                AppTextField(text = uiState.name, hint = "Age", onValueChange = { })
                AppTextField(text = uiState.name, hint = "Job", onValueChange = { })
                AppTextField(
                    text = uiState.name,
                    hint = "gender",
                    onValueChange = { })
            }
        }

    }
}

@Composable
fun AppTextField(
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter? = null,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val containerColor = Color.White
    val borderColor = Color.Gray
    val textColor = Color.Black
    val errorColor = Color.Red

    OutlinedTextField(
        value = text.trim(),
        onValueChange = onValueChange,
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
        placeholder = {
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

@Composable
@Preview
fun HomeContentPreview() {
    HomeContent(uiState = UserUiState(name = "Aziza"), onNameChanged = {})
}