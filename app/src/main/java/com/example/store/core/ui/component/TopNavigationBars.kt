package com.example.store.core.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.core.ui.theme.StoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreCenteredTopBar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean = true,
    elevation: Dp = 0.dp,
    onNavigateUp: () -> Unit = {},
    action:  @Composable () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        shadowElevation = elevation
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier,
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            },
            navigationIcon = {
                if (canNavigateBack) {
                    IconButton(onClick = onNavigateUp) {
                        Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                    }
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface
            ),
            actions = { action() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreLargeTopBar(
    modifier: Modifier = Modifier,
    title: String,
    canNavigateBack: Boolean = true,
    onNavigateUp: () -> Unit = {},
    action:  @Composable () -> Unit = {}
) {
    MediumTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
        },
        modifier = modifier,
        actions = { action() },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                }
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    StoreTheme {
        StoreLargeTopBar(
            title = "Title",
            onNavigateUp = {}
        )
    }
}

