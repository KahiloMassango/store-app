package com.example.store.features.store.components


import com.example.store.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.LocalTime

@Composable
internal fun OpeningDetail(
    modifier: Modifier = Modifier,
    openingTime: String,
    closingTime: String
) {
    val isOpen = LocalTime.parse(openingTime) <= LocalTime.now()
            && LocalTime.now() <= LocalTime.parse(closingTime)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(27.dp),
            painter = painterResource(R.drawable.ic_time),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outlineVariant
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "Hórario de Funcionamento",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "$openingTime - $closingTime",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Box(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.outlineVariant, CircleShape)
                        .size(5.dp)
                        .clip(RoundedCornerShape(50))
                )
                Text(
                    text = if (isOpen) "Aberto" else "Fechado",
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isOpen) MaterialTheme.colorScheme.scrim else MaterialTheme.colorScheme.onError,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}