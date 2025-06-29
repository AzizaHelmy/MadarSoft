package com.example.madarsoft.presentation.screen.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.madarsoft.R
import com.example.madarsoft.presentation.navigation.localNavController
import com.example.madarsoft.presentation.screen.component.AppScaffold

/**
 * Created by Aziza Helmy on 26/06/2025.
 */

@Composable
fun DetailsScreen(viewModel: DetailsViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    DetailsContent(uiState = state.user)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsContent(uiState: UserUiState) {
    val navController = localNavController.current

    AppScaffold(
        title = stringResource(R.string.user_details),
        onBack = { navController.navigateUp() },
        containerColor = Color(0xFFF8F8F8)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    UserInfoRow(
                        label = "${stringResource(R.string.name)}:",
                        value = uiState.name
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 2.dp),
                        color = Color.LightGray
                    )
                    UserInfoRow(
                        label = "${stringResource(R.string.title)}:",
                        value = uiState.title
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 2.dp),
                        color = Color.LightGray
                    )
                    UserInfoRow(label = "${stringResource(R.string.age)}:", value = uiState.age)
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 2.dp),
                        color = Color.LightGray
                    )
                    UserInfoRow(label = "${stringResource(R.string.job)}:", value = uiState.job)
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 2.dp),
                        color = Color.LightGray
                    )
                    UserInfoRow(label = stringResource(R.string.gender), value = uiState.gender)
                }
            }
        }
    }
}

@Composable
fun UserInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = Color.DarkGray
        )
        Text(
            text = value.ifBlank { "-" },
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )
    }
}
