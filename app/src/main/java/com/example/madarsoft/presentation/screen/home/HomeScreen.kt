package com.example.madarsoft.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.madarsoft.R
import com.example.madarsoft.presentation.navigation.Destination
import com.example.madarsoft.presentation.navigation.localNavController
import com.example.madarsoft.presentation.screen.component.AppTextField
import com.example.madarsoft.presentation.screen.component.Gender
import com.example.madarsoft.presentation.screen.component.GenderDropDown
import com.example.madarsoft.presentation.theme.Blue

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val navController = localNavController.current

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Destination.Details)
            viewModel.resetSuccessState()
        }
    }

    if (state.showDialog) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissDialog() },
            title = { Text(stringResource(R.string.error)) },
            text = { Text(state.error) },
            confirmButton = {
                TextButton(onClick = { viewModel.dismissDialog() }) {
                    Text(stringResource(R.string.ok))
                }
            }
        )
    }

    HomeContent(
        uiState = state.user,
        isLoading = state.isLoading,
        onNameChanged = viewModel::onNameChanged,
        onTitleChanged = viewModel::onTitleChanged,
        onAgeChanged = viewModel::onAgeChanged,
        onJobChanged = viewModel::onJobChanged,
        onGenderChanged = viewModel::onGenderChanged,
        onAddClicked = viewModel::addUser
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    uiState: UserUiState,
    isLoading: Boolean = false,
    onNameChanged: (String) -> Unit = {},
    onTitleChanged: (String) -> Unit = {},
    onAgeChanged: (String) -> Unit = {},
    onJobChanged: (String) -> Unit = {},
    onGenderChanged: (Gender) -> Unit = {},
    onAddClicked: (UserUiState) -> Unit = {}
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(R.string.add_user), color = Color.White) },
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
                    hint = stringResource(R.string.name),
                    onValueChange = onNameChanged
                )
                AppTextField(
                    text = uiState.title,
                    hint = stringResource(R.string.title),
                    onValueChange = onTitleChanged
                )
                AppTextField(
                    text = uiState.age.toString(),
                    hint = stringResource(R.string.age),
                    keyboardType = KeyboardType.Number,
                    onValueChange = {
                        onAgeChanged(it.filter { char -> char.isDigit() })
                    })
                AppTextField(
                    text = uiState.job,
                    hint = stringResource(R.string.job),
                    onValueChange = onJobChanged
                )
                GenderDropDown(
                    selectedGender = uiState.gender,
                    onGenderSelected = onGenderChanged
                )


                Button(
                    onClick = { onAddClicked(uiState) },
                    enabled = uiState.isValidData && !isLoading,
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
                    if (isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(text = stringResource(R.string.add))
                    }
                }
            }
        }

    }
}

@Composable
@Preview
fun HomeContentPreview() {
    HomeContent(uiState = UserUiState(name = "Aziza"), onNameChanged = {})
}