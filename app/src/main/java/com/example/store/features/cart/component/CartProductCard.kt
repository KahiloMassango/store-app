package com.example.store.features.cart.component


import com.example.store.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.cart.cartProductItem
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.theme.defaultFont
import com.example.store.core.ui.util.toCurrency

@ExperimentalMaterial3Api
@Composable
internal fun CartProductCard(
    modifier: Modifier = Modifier,
    cartItem: CartProductItem,
    onClick: (String) -> Unit,
    onIncreaseQty: () -> Unit,
    onDecreaseQty: () -> Unit,
    onRemove: (String) -> Unit
) {

    Card(
        onClick = { onClick(cartItem.productId) },
        modifier = modifier.heightIn(min = 100.dp, max = 106.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                model = cartItem.imageUrl,
                placeholder = painterResource(R.drawable.detail_image_ex2),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = cartItem.name,
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Column(
                            modifier = Modifier,
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                        ) {
                            if (cartItem.color != null) {
                                Attribute(attribute = "Cor", value = cartItem.color!!)
                            }
                            if (cartItem.size != null) {
                                Attribute(attribute = "Tamanho", value = cartItem.size!!)
                            }
                        }
                    }
                    IconButton(
                        modifier = Modifier.size(22.dp),
                        onClick = { onRemove(cartItem.id) }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    QuantitySelector(
                        stockQuantity = cartItem.stockQuantity,
                        quantity = cartItem.quantity,
                        onDecrease = { if (cartItem.quantity > 1) onDecreaseQty() },
                        onIncrease = onIncreaseQty
                    )
                    Text(
                        text = cartItem.price.toCurrency(),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


@Composable
private fun Attribute(
    modifier: Modifier = Modifier,
    attribute: String,
    value: String
) {
    val stringBuilder = buildAnnotatedString {
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.inverseOnSurface,
                fontFamily = defaultFont,
                fontWeight = FontWeight.Normal
            )
        ) { append("${attribute}:") }
        append(" ")
        withStyle(
            SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontFamily = defaultFont,
                fontWeight = FontWeight.SemiBold
            )
        ) { append(value) }
    }

    Text(
        modifier = modifier,
        text = stringBuilder,
        style = MaterialTheme.typography.labelSmall,
        fontSize = 10.sp
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CartProductCard(
            cartItem = cartProductItem.copy(name = "this is a very long product name"),
            onClick = { },
            onRemove = {},
            onIncreaseQty = {},
            onDecreaseQty = {}
        )
    }
}