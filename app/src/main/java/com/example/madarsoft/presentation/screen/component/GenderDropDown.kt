package com.example.madarsoft.presentation.screen.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Created by Aziza Helmy on 28/06/2025.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderDropDown(
    selectedGender: Gender?,
    onGenderSelected: (Gender) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val genderOptions = Gender.entries.toTypedArray()

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedGender?.displayName ?: "",
            onValueChange = {},
            readOnly = true,
            label = { Text("Gender") },
            placeholder = { Text("Select gender") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor().fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            genderOptions.forEach { gender ->
                DropdownMenuItem(
                    text = { Text(gender.displayName) },
                    onClick = {
                        onGenderSelected(gender)
                        expanded = false
                    }
                )
            }
        }
    }
}

enum class Gender(val displayName: String) {
    Male("Male"),
    Female("Female");

    companion object {
        fun fromDisplayName(name: String): Gender {
            return values().firstOrNull { it.displayName == name } ?: Male
        }
    }
}


