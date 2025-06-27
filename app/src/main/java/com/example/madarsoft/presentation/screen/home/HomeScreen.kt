package com.example.madarsoft.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.madarsoft.presentation.theme.Blue

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeContent(
        uiState = state.user,
        onNameChanged = viewModel::onNameChanged,
        onTitleChanged = viewModel::onTitleChanged,
        onAgeChanged = viewModel::onAgeChanged,
        onJobChanged = viewModel::onJobChanged,
        onAddClicked = viewModel::addUser
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: UserUiState,
    onNameChanged: (String) -> Unit = {},
    onTitleChanged: (String) -> Unit = {},
    onAgeChanged: (String) -> Unit = {},
    onJobChanged: (String) -> Unit = {},
    onGenderChanged: (String) -> Unit = {},
    onAddClicked: (UserUiState) -> Unit = {}
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Add User", color = Color.White) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Blue)
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AppTextField(
                    modifier = Modifier.padding(top = 16.dp),
                    text = uiState.name,
                    hint = "Name",
                    onValueChange = onNameChanged
                )
                AppTextField(text = uiState.title, hint = "Title", onValueChange = onTitleChanged)
                AppTextField(
                    text = uiState.age.toString(),
                    hint = "Age",
                    onValueChange = onAgeChanged
                )
                AppTextField(text = uiState.job, hint = "Job", onValueChange = onJobChanged)
                AppTextField(
                    text = uiState.gender,
                    hint = "gender",
                    onValueChange = onGenderChanged
                )
                Button(
                    onClick = { onAddClicked },
                    enabled = uiState.isValidData,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(size = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = Color.White,
                        disabledContainerColor = Color.LightGray,
                        disabledContentColor = Color.Gray
                    )
                ) {
                    Text(text = "Add")
                }
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
    val containerColor = Color.Transparent
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

@Composable
@Preview
fun HomeContentPreview() {
    HomeContent(uiState = UserUiState(name = "Aziza"), onNameChanged = {})
}