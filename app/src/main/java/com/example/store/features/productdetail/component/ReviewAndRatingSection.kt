package com.example.store.features.productdetail.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.core.model.RatingInfo
import com.example.store.core.ui.theme.StoreTheme

@Composable
internal fun ReviewAndRatingSection(
    modifier: Modifier = Modifier,
    ratingInfo: RatingInfo
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Comentários & Avaliações",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "4.5",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Classificação",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "${ratingInfo.totalRatings} avaliações",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
    }

}


@Composable
private fun RatingAverage(
    modifier: Modifier = Modifier,
    average: Double,
    totalRatings: Int
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = average.toString(),
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "$totalRatings avaliações",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}


@Preview
@Composable
private fun Preview() {
    StoreTheme {
        ReviewAndRatingSection(
            ratingInfo = RatingInfo(
                "",
                "",
                0,
                0,
                listOf(0, 0, 0, 0, 0)
            )
        )
    }
}