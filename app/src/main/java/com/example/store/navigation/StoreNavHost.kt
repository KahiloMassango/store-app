package com.example.store.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.features.orders.navigation.navigateToMyOrders
import com.example.features.orders.navigation.ordersScreen
import com.example.store.R
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.feature.product_listing.navigation.navigateToProductListing
import com.example.store.feature.product_listing.navigation.productListingScreen
import com.example.store.feature.reviews.navigation.navigateToReviews
import com.example.store.feature.reviews.navigation.reviewsScreen
import com.example.store.feature.shop.navigation.navigateToShop
import com.example.store.feature.shop.navigation.shopScreen
import com.example.store.features.authentication.forgot.navigation.forgotPasswordScreen
import com.example.store.features.authentication.forgot.navigation.navigateToForgotPassword
import com.example.store.features.authentication.login.navigation.LoginRoute
import com.example.store.features.authentication.login.navigation.loginScreen
import com.example.store.features.authentication.signup.navigation.navigateToSignUp
import com.example.store.features.authentication.signup.navigation.signUpScreen
import com.example.store.features.cart.navigation.cartScreen
import com.example.store.features.checkout.navigation.checkoutScreen
import com.example.store.features.checkout.navigation.navigateToCheckout
import com.example.store.features.discover.navigation.categoryScreen
import com.example.store.features.home.navigation.HomeRoute
import com.example.store.features.home.navigation.homeScreen
import com.example.store.features.newaddress.navigation.navigateToNewAddressScreen
import com.example.store.features.newaddress.navigation.newAddressScreen
import com.example.store.features.productdetail.navigation.navigateToProductDetail
import com.example.store.features.productdetail.navigation.productDetailScreen
import com.example.store.features.search.navigation.navigateToSearch
import com.example.store.features.search.navigation.searchScreen
import com.example.store.features.store.navigation.navigateToStore
import com.example.store.features.store.navigation.storeScreen
import com.example.store.features.userprofile.changepassword.navigation.changePasswordScreen
import com.example.store.features.userprofile.changepassword.navigation.navigateToChangePassword
import com.example.store.features.userprofile.deliveryaddress.navigation.deliveryAddressesScreen
import com.example.store.features.userprofile.deliveryaddress.navigation.navigateToAddresses
import com.example.store.features.userprofile.editprofile.navigation.editProfileScreen
import com.example.store.features.userprofile.editprofile.navigation.navigateToEditProfile
import com.example.store.features.userprofile.helpcenter.navigation.helpCenterScreen
import com.example.store.features.userprofile.helpcenter.navigation.navigateToHelpCenter
import com.example.store.features.userprofile.orderdeail.navigation.navigateToOrderDetail
import com.example.store.features.userprofile.orderdeail.navigation.orderDetailScreen
import com.example.store.features.userprofile.policyprivacy.navigation.navigateToPolicePrivacy
import com.example.store.features.userprofile.policyprivacy.navigation.policePrivacyScreen
import com.example.store.features.userprofile.profile.navigation.profileScreen
import com.example.store.navigation.navigation.BottomNavigationBar
import com.example.store.navigation.navigation.StoreNavigationRail


@Composable
fun App(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    isLoggedIn: Boolean,
    cartItemsCount: Int,
    windowSize: WindowWidthSizeClass,
) {
    val startDestination: Any = if (isLoggedIn) HomeRoute else LoginRoute

    // Observe login state and navigate to LoginRoute if the user logs out
   /* LaunchedEffect(isLoggedIn) {
        if (!isLoggedIn) {
            navController.navigate(LoginRoute) {
                popUpTo(0) { inclusive = true } // Clears back stack completely
            }
        }
    }*/

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                AppContent(
                    modifier = modifier.weight(1f),
                    startDestination = startDestination,
                    navController = navController,
                )
                BottomNavigationBar(
                    navController = navController,
                    cartItemsCount = cartItemsCount
                )
            }
        }

        else -> {
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                StoreNavigationRail(
                    navController = navController,
                    cartItemsCount = cartItemsCount
                )
                AppContent(
                    modifier = modifier.weight(1f),
                    startDestination = startDestination,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun AppContent(
    modifier: Modifier = Modifier,
    startDestination: Any,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        loginScreen(
            onSignUp = { navController.navigateToSignUp() },
            onForgotPassword = { navController.navigateToForgotPassword() },
            onNavigateUp = navController::navigateUp,
            onLogin = {
                navController.navigate(HomeRoute) {
                    popUpTo(LoginRoute) {
                        inclusive = true
                    }
                }
            }
        )

        signUpScreen(
            onLogin = {
                navController.navigate(LoginRoute) {
                    popUpTo(LoginRoute) { inclusive = false }
                }
            },
            onNavigateUp = navController::navigateUp
        )

        forgotPasswordScreen(
            onNavigateUp = navController::navigateUp
        )

        profileScreen(
            onMyOrdersClick = { navController.navigateToMyOrders() },
            onEditProfileClick = { navController.navigateToEditProfile() },
            onChangePasswordClick = { navController.navigateToChangePassword() },
            onHelpCenterClick = { navController.navigateToHelpCenter() },
            onAddressesClick = { navController.navigateToAddresses() },
            onPolicePrivacyClick = { navController.navigateToPolicePrivacy() }
        )

        categoryScreen(
            onSearch = { navController.navigateToSearch() },
            onSelectCategory = { category, subcategory ->
                navController.navigateToShop(category, subcategory)
            }
        )


        shopScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onNavigateUp = navController::navigateUp
        )


        homeScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onSeeAll = { navController.navigateToProductListing(it) },
            onSearch = { navController.navigateToSearch() }
        )

        cartScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onCheckout = { navController.navigateToCheckout() }
        )

        searchScreen(
            onNavigateUp = navController::navigateUp,
            onProductClick = { navController.navigateToProductDetail(it) }
        )

        policePrivacyScreen(
            onNavigateUp = navController::navigateUp
        )

        helpCenterScreen(
            onNavigationUp = navController::navigateUp
        )

        ordersScreen(
            onOrderClick = { navController.navigateToOrderDetail(it) },
            onNavigateUp = navController::navigateUp
        )

        orderDetailScreen(
            onNavigateUp = navController::navigateUp
        )

        changePasswordScreen(
            onNavigateUp = navController::navigateUp
        )

        editProfileScreen(
            onNavigateUp = navController::navigateUp
        )

        deliveryAddressesScreen(
            onAddNewAddress = { navController.navigateToNewAddressScreen() },
            onNavigateUp = navController::navigateUp
        )

        productListingScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onNavigateUp = navController::navigateUp
        )

        productDetailScreen(
            onReviewsClick = { navController.navigateToReviews(it) },
            onNavigateUp = navController::navigateUp,
            onSuggestedProductsClick = { navController.navigateToProductDetail(it) },
            onStoreClick = { navController.navigateToStore(it) }
        )

        storeScreen(
            onProductClick = { navController.navigateToProductDetail(it) },
            onNavigateUp = navController::navigateUp
        )


        checkoutScreen(
            onNavigateUp = navController::navigateUp,
            onAddAddress = { navController.navigateToNewAddressScreen() }
        )

        newAddressScreen(onNavigateUp = navController::navigateUp)

        reviewsScreen(onNavigateUp = navController::navigateUp)
    }
}

@Composable
fun NoInternetConnection(
    modifier: Modifier = Modifier,
    onTryAgain: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(R.drawable.ic_no_internet),
            contentDescription = null,
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Nenhuma conexão de internet foi encontrada. Verifique sua conexão e tente novamente",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(24.dp))
        Button(
            onTryAgain
        ) {
            Text(text = "Tentar novamente")
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        //App()
    }
}




