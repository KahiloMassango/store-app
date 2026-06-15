package com.example.store.features.cart.component


import com.example.store.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreLargeTopBar

@Composable
internal fun EmptyCartContent(modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreLargeTopBar(
                modifier = Modifier
                    .background(Color.Red)
                    .clip(GenericShape { size, _ ->
                        lineTo(size.width, 0f)
                        lineTo(size.width, Float.MAX_VALUE)
                        lineTo(0f, Float.MAX_VALUE)
                    })
                    .shadow(8.dp),
                title = "Meu Cesto", canNavigateBack = false
            )
        },
        contentWindowInsets = WindowInsets.navigationBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.empty_cart),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = "Seu cesto está vazio",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Parece que você não adicionou nada ao seu cesto. Vá em frente e explore as principais categorias.",
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}