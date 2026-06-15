package com.example.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.navigation.App
import com.example.store.navigation.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val appViewModel: AppViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        installSplashScreen().apply {
            setKeepOnScreenCondition {
                appViewModel.showSplashScreen
            }
        }

        enableEdgeToEdge()
        setContent {
            StoreTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    val isLoggedIn by appViewModel.isLoggedInFlow.collectAsState(false)
                    val cartItemsCount by appViewModel.cartCount.collectAsStateWithLifecycle()
                    val isOffline by appViewModel.isOffline.collectAsStateWithLifecycle()
                    App(
                        isLoggedIn = isLoggedIn,
                        cartItemsCount = cartItemsCount,
                        windowSize = windowSize.widthSizeClass,
                    )
                }
            }
        }
    }
}
