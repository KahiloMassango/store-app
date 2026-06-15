package com.example.store.features.productdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.model.Category
import com.example.store.core.model.product.ProductItem
import com.example.store.core.model.product.ProductWithVariation
import com.example.store.core.ui.ErrorScreen
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.core.ui.util.PhonePreviews
import com.example.store.features.productdetail.component.AddToCartContainer
import com.example.store.features.productdetail.component.AttributeSelector
import com.example.store.features.productdetail.component.ProductAttributes
import com.example.store.features.productdetail.component.ProductDetailLoadingScreen
import com.example.store.features.productdetail.component.ProductDetails
import com.example.store.features.productdetail.component.ProductImageCarousel


@Composable
internal fun ProductDetailsScreen(
    modifier: Modifier = Modifier,
    onStoreClick: (String) -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    onReviewsClick: (String) -> Unit,
    onSuggestedProductClick: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val selectedColor by viewModel.selectedColor.collectAsStateWithLifecycle()
    val selectedSize by viewModel.selectedSize.collectAsStateWithLifecycle()

    val filteredColors by viewModel.filteredColors.collectAsStateWithLifecycle()
    val filteredSizes by viewModel.filteredSizes.collectAsStateWithLifecycle()

    // Display the selected combination's price and stock
    val selectedItem by viewModel.selectedItem.collectAsStateWithLifecycle()

    val productImages = viewModel.productImages.value


    when (uiState) {
        is ProductDetailState.Loading -> ProductDetailLoadingScreen(onNavigateUp)
        is ProductDetailState.Error -> ErrorScreen(onTryAgain = viewModel::refresh)
        is ProductDetailState.Success -> ProductDetailContent(
            modifier = modifier,
            product = uiState.product,
            productImages = productImages,
            isFavorite = false,
            onStoreClick = onStoreClick,
            onNavigateUp = onNavigateUp,
            onReviewsClick = { onReviewsClick(it) },
            onAddToCart = viewModel::addCart,
            onAddFavorite = {},
            selectedItem = selectedItem,
            selectedColor = selectedColor,
            selectedSize = selectedSize,
            filteredColors = filteredColors,
            filteredSizes = filteredSizes,
            onSelectColor = viewModel::selectColor,
            onSelectSize = viewModel::selectSize,
        )
    }

}

@Composable
private fun ProductDetailContent(
    modifier: Modifier = Modifier,
    product: ProductWithVariation,
    productImages: List<String>,
    isFavorite: Boolean,
    onAddFavorite: () -> Unit,
    onReviewsClick: (String) -> Unit,
    onAddToCart: () -> Unit,
    onStoreClick: (String) -> Unit,
    selectedItem: ProductItem?,
    selectedColor: String?,
    selectedSize: String?,
    filteredColors: List<String>,
    filteredSizes: List<String>,
    onSelectColor: (String) -> Unit,
    onSelectSize: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current
    var showColorSelector by remember { mutableStateOf(false) }
    var showSizeSelector by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            StoreCenteredTopBar(
                modifier = Modifier,
                title = product.name,
                canNavigateBack = true,
                onNavigateUp = onNavigateUp
            )
        },
        contentWindowInsets = WindowInsets.navigationBars.exclude(BottomAppBarDefaults.windowInsets)
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize(),
                ) {

                    ProductImageCarousel(images = productImages)

                    Spacer(modifier = Modifier.height(16.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    ) {
                        ProductAttributes(
                            modifier = Modifier,
                            category = product.category,
                            selectedSize = selectedSize,
                            selectedColor = selectedColor,
                            onShowSizeOptions = { showSizeSelector = true },
                            onShowColorOptions = { showColorSelector = true }
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        ProductDetails(
                            modifier = Modifier,
                            onStoreClick = { onStoreClick(product.storeId) },
                            name = product.name,
                            description = product.description,
                            storeName = product.storeName,
                        )

                        Spacer(modifier = Modifier.height(36.dp))
                        AddToCartContainer(
                            modifier = Modifier
                                .fillMaxWidth(),
                            price = selectedItem?.price ?: 0,
                            onAddToCart = {
                                onAddToCart()
                            }
                        )
                    }
                }

            }
        }
        if (showSizeSelector) {
            AttributeSelector(
                label = "Selecione tamanho",
                selectedOption = selectedSize,
                options = filteredSizes.map { it.uppercase() },
                onSelectOption = {
                    onSelectSize(it)
                },
                onDismissRequest = { showSizeSelector = false }
            )
        }
        if (showColorSelector) {
            AttributeSelector(
                label = "Selecione cor",
                selectedOption = selectedColor,
                options = filteredColors,
                onSelectOption = {
                    onSelectColor(it)
                },
                onDismissRequest = { showColorSelector = false }
            )
        }
    }
}

@PhonePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProductDetailContent(
            product = mockProductWithVariation,
            productImages = listOf(mockProductWithVariation.image),
            isFavorite = false,
            onAddFavorite = { TODO() },
            onReviewsClick = { TODO() },
            onAddToCart = { TODO() },
            onStoreClick = { TODO() },
            selectedItem = mockProductWithVariation.productItems[0],
            selectedColor = null,
            selectedSize = null,
            filteredColors = listOf(),
            filteredSizes = listOf(),
            onSelectColor = {  },
            onSelectSize = {  },
            onNavigateUp = {  }
        )
    }
}

val mockProductWithVariation = ProductWithVariation(
    id = "1",
    description = "A stylish and comfortable t-shirt made from high-quality cotton.",
    image = "https://example.com/product_image.jpg",
    storeId = "100",
    storeName = "Fashion Store",
    name = "Cotton T-Shirt",
    category = Category("Roupas", true, true), // Ajuste conforme a sua enumeração de categorias
    productItems = listOf(
        ProductItem(
            id = "101",
            color = "Red",
            image = "https://example.com/red_tshirt.jpg",
            price = 2500,
            size = "M",
            stockQuantity = 10
        ),
        ProductItem(
            id = "102",
            color = "Blue",
            image = "https://example.com/blue_tshirt.jpg",
            price = 2500,
            size = "L",
            stockQuantity = 8
        ),
        ProductItem(
            id = "103",
            color = "Black",
            image = "https://example.com/black_tshirt.jpg",
            price = 2700,
            size = "XL",
            stockQuantity = 5
        )
    )
)
