package com.example.store.features.userprofile.helpcenter

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.StoreCenteredTopBar
import com.example.store.core.ui.theme.StoreTheme
import com.example.store.features.userprofile.helpcenter.contact_us.ContactUsContent
import com.example.store.features.userprofile.helpcenter.faq.FaqContent
import com.example.store.features.userprofile.helpcenter.faq.components.HelpCenterTabRow

@Composable
internal fun HelpCenterScreen(
    modifier: Modifier = Modifier,
    onNavigationUp: () -> Unit = {}
) {
    var currentTab by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            StoreCenteredTopBar(
                title = "Centro de ajuda",
                elevation = 4.dp,
                canNavigateBack = true,
                onNavigateUp = onNavigationUp
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            HelpCenterTabRow(
                modifier = Modifier,
                selectedTab = currentTab,
                onTabClick = { currentTab = it }
            )
            Spacer(modifier = Modifier.height(24.dp))
            AnimatedContent(
                targetState = currentTab,
                label = "",
                transitionSpec = {
                    if(targetState > initialState) {
                        slideInHorizontally { width -> width  }  togetherWith
                                slideOutHorizontally { width -> -width  }
                    } else {
                        slideInHorizontally { width -> -width  }  togetherWith
                                slideOutHorizontally { width -> width  }
                    }
                }
            ) { tab ->
                when (tab) {
                    0 -> FaqContent()
                    else -> ContactUsContent()
                }
            }
        }
    }
}


@PreviewLightDark
@Composable
private fun Preview() {
    StoreTheme {
        HelpCenterScreen()
    }
}

