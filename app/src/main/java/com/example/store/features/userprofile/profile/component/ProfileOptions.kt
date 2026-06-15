package com.example.store.features.userprofile.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.store.core.ui.component.ThemePreviews
import com.example.store.core.ui.theme.StoreTheme

@Composable
internal fun ProfileOptions(
    modifier: Modifier = Modifier,
    onMyOrdersClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onChangePasswordClick: () -> Unit,
    onHelpCenterClick: () -> Unit,
    onAddressesClick: () -> Unit,
    onPolicePrivacyClick: () -> Unit

) {
    Column(
        modifier = modifier
    ) {
        ProfileOptionItem(
            option = "Editar Perfil",
            description = "Altere suas informações",
            onClick = onEditProfileClick
        )

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOptionItem(
            option = "Alterar senha",
            description = "Mantenha-se seguro",
            onClick = onChangePasswordClick
        )

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOptionItem(
            option = "Minhas encomendas",
            description = "Histórico de encomendas",
            onClick =  onMyOrdersClick
        )

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOptionItem(
            option = "Endereços de entrega",
            description = "Gerencie seus endereços de entrega",
            onClick = onAddressesClick
        )

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOptionItem(
            option = "Centro de ajuda",
            description = "Encontre respostas para suas dúvidas.",
            onClick = onHelpCenterClick
        )

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.inverseOnSurface)

        ProfileOptionItem(
            option = "Política de privacidade",
            description = "Saiba como seus dados são usados.",
            onClick = onPolicePrivacyClick
        )
    }
}

@Composable
private fun ProfileOptionItem(
    modifier: Modifier = Modifier,
    option: String,
    description: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.inverseOnSurface,
                    fontWeight = FontWeight.Normal
                )
            }
            Icon(
                modifier = Modifier.size(16.dp),
                imageVector = Icons. AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}


@ThemePreviews
@Composable
private fun Preview() {
    StoreTheme {
        ProfileOptions(
            onMyOrdersClick = {},
            onEditProfileClick = {},
            onChangePasswordClick = {},
            onHelpCenterClick = {},
            onAddressesClick = {},
            onPolicePrivacyClick = {}
        )
    }
}
