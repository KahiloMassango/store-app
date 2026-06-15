package com.example.store.features.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.cart.CartProductItem
import com.example.store.core.model.cart.cartProductItem
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreLargeTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews
import com.example.store.core.ui.util.toCurrency
import com.example.store.features.cart.component.CartProductCard
import com.example.store.features.cart.component.EmptyCartContent

@Composable
internal fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    onProductClick: (String) -> Unit,
    onCheckout: () -> Unit,
) {
    val products by viewModel.uiState.collectAsStateWithLifecycle()
    val cartTotal by viewModel.cartTotal.collectAsStateWithLifecycle()

    when {
        products.isEmpty() -> EmptyCartContent()
        else -> CartContent(
            products = products,
            cartTotal = cartTotal,
            onProductClick = { onProductClick(it) },
            onRemoveCartItem = { viewModel.removeProductFromCart(it) },
            onUpdateQuantity = { id, qty -> viewModel.updateQuantity(id, qty) },
            onCheckout = onCheckout
        )
    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartContent(
    modifier: Modifier = Modifier,
    products: List<CartProductItem>,
    cartTotal: Int,
    onProductClick: (String) -> Unit,
    onRemoveCartItem: (String) -> Unit,
    onUpdateQuantity: (String, Int) -> Unit,
    onCheckout: () -> Unit,
) {
    Scaffold(
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
        Surface(
            modifier = modifier.padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),

                ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(22.dp)
                ) {
                    items(products) { product ->
                        CartProductCard(
                            modifier = Modifier,
                            cartItem = product,
                            onClick = { onProductClick(it) },
                            onRemove = { onRemoveCartItem(it) },
                            onIncreaseQty = {
                                onUpdateQuantity(
                                    product.id,
                                    product.quantity + 1
                                )
                            },
                            onDecreaseQty = {
                                onUpdateQuantity(
                                    product.id,
                                    product.quantity - 1
                                )
                            }
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(bottom = 10.dp, top = 14.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Montante total:",
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.inverseOnSurface,
                        )
                        Text(
                            text = cartTotal.toCurrency(),
                            color = MaterialTheme.colorScheme.onSurface,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    CustomButton(
                        text = "Continuar",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onCheckout
                    )
                }
            }

        }
    }
}

@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        CartContent(
            products = listOf(cartProductItem, cartProductItem, cartProductItem, cartProductItem),
            cartTotal = 67614,
            onProductClick = {  },
            onRemoveCartItem = {  },
            onUpdateQuantity = { _, _ -> },
            onCheckout = {  }
        )
    }
}