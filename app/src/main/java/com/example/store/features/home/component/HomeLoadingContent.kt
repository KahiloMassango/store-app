package com.example.store.features.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.ShimmerBox
import com.example.store.core.ui.component.SearchField
import com.example.store.core.ui.component.skeletons.ProductCardSkeleton

@Preview
@Composable
internal fun HomeLoadingContent(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            SearchField(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                query = "",
                placeholder = "Pesquise por lojas, roupas, calçados, acessórios, etc.",
                enabled = false,
                onQueryChange = {},
                onSearch = {},
                onClearQuery = {},
            )
            Spacer(modifier = Modifier.height(18.dp))
            ShimmerBox(Modifier.fillMaxWidth().height(200.dp), 0)
            Spacer(modifier = Modifier.height(18.dp))
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(26.dp)
            ) {
                SectionSkeleton()

                SectionSkeleton()
            }
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

@Composable
private fun SectionSkeleton() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        ShimmerBox(
            Modifier
                .width(60.dp)
                .height(12.dp),
            cornerRadius = 20
        )
        Spacer(Modifier.height(4.dp))
        ShimmerBox(
            Modifier
                .width(100.dp)
                .height(10.dp),
            cornerRadius = 20
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            repeat(6) {
                ProductCardSkeleton()
            }
        }
    }
}

