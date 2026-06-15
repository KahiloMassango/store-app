package com.example.store.features.userprofile.editprofile.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.core.model.Gender

@Composable
internal fun GenderSelector(
    modifier: Modifier = Modifier,
    selectedGender: String,
    onSelectGender: (String) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "Género",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            if (! enabled) {
                Text(
                    text = "(Não alterável)",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("AS", "ff").forEach { gender ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = gender == selectedGender,
                        onClick = { if (enabled) onSelectGender(gender) },
                        enabled = enabled
                    )
                    Text(
                        text = gender,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}