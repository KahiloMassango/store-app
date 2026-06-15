package com.example.store.features.userprofile.policyprivacy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreCenteredTopBar

@Preview
@Composable
internal fun PolicePrivacyScreen(
    modifier: Modifier = Modifier,
    onNavigationUp: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            StoreCenteredTopBar(
                title = "Política de Privacidade",
                elevation = 4.dp,
                canNavigateBack = true,
                onNavigateUp = onNavigationUp
            )
        }

    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Polítia de Cancelamento",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    //fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and " +
                            "typesetting industry. Lorem Ipsum has been the industry's " +
                            "standard dummy text ever since the 1500s, when an unknown printer " +
                            "took a galley of type and scrambled it to make a type specimen book. " +
                            "It has survived not only five centuries, but also the leap into " +
                            "electronic typesetting, remaining essentially unchanged.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Justify
                    //fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.height(34.dp))
                Text(
                    text = "Termos e Condições",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Lorem Ipsum is simply dummy text of the printing and " +
                            "typesetting industry. Lorem Ipsum has been the industry's " +
                            "standard dummy text ever since the 1500s, when an unknown printer " +
                            "took a galley of type and scrambled it to make a type specimen book. " +
                            "It has survived not only five centuries, but also the leap into " +
                            "electronic typesetting, remaining essentially unchanged.",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Justify
                    //fontSize = 18.sp,
                )

            }
        }
    }
}