package com.example.store.features.productdetail.component


import com.example.store.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.store.core.model.Rating
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
internal fun ReviewCard(
    modifier: Modifier = Modifier,
    rating: Rating
) {

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(50)),
                    painter = painterResource(R.drawable.men_clothes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Kahilo Pedro Massango",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            StarRating(rating = rating.rate)
            Text(
                text = dateFormatter(rating.ratingDate),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(Modifier.height(10.dp))
        Text(
            text = rating.comment!!,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Start,
            lineHeight = 22.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}


@Composable
private fun StarRating(
    modifier: Modifier = Modifier,
    rating: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (starIndex in 1..5) {
            val isFilled = starIndex <= rating
            val starIcon = if (isFilled) R.drawable.filled_star else R.drawable.outlined_star
            val starColor = Color(0xFFFFBA49)

            Icon(
                modifier = Modifier
                    .padding(end = 3.dp)
                    .size(16.dp),
                painter = painterResource(id = starIcon),
                contentDescription = "Star $starIndex",
                tint = starColor
            )
        }
    }
}

private fun dateFormatter(dateString: String): String {
    // Parse the input date string to a LocalDateTime
    val datePattern = "yyyy-MM-dd"
    val dateFormatter = DateTimeFormatter.ofPattern(datePattern)
    val inputDate =  LocalDate.parse(dateString, dateFormatter).atStartOfDay()

    // Get the current date and time
    val now = LocalDateTime.now()

    // Calculate the difference in days and months
    val days = ChronoUnit.DAYS.between(inputDate.toLocalDate(), now.toLocalDate())
    val months = ChronoUnit.MONTHS.between(inputDate.toLocalDate(), now.toLocalDate())

    return when {
        months > 0 -> "$months month${if (months != 1L) "s" else ""} ago"
        days > 0 -> "$days day${if (days != 1L) "s" else ""} ago"
        else -> "Today"  // Less than a day ago
    }
}
