package com.example.store.features.productdetail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.ShimmerBox

@Preview
@Composable
internal fun ProductDetailLoadingScreen(
    onNavigateUp: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Row(
                Modifier.statusBarsPadding().fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onNavigateUp) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = null)
                }
                Box(
                    Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    ShimmerBox(
                        Modifier
                            .fillMaxWidth(0.6f)
                            .height(18.dp)
                    )
                }
            }
        },
        contentWindowInsets = WindowInsets.navigationBars.exclude(BottomAppBarDefaults.windowInsets)

    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
        ) {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
            ) {
                ShimmerBox(
                    Modifier.fillMaxWidth().height(410.dp), // Images
                    cornerRadius = 0
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        //.weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ShimmerBox(
                            Modifier.weight(1f).height(38.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        ShimmerBox(
                            Modifier.weight(1f).height(38.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    ShimmerBox(
                        Modifier
                            .fillMaxWidth(0.5f)
                            .height(14.dp),
                        cornerRadius = 20
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    ShimmerBox(
                        Modifier
                            .fillMaxWidth(0.8f)
                            .height(16.dp),
                        cornerRadius = 20
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    ShimmerBox(
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        cornerRadius = 20
                    )

                    Spacer(modifier = Modifier.height(36.dp))
                    ShimmerBox(
                        Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                        cornerRadius = 20
                    )
                }
            }
        }
    }
}