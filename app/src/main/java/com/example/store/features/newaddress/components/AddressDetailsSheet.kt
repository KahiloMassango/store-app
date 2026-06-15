package com.example.store.features.newaddress.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.store.core.model.AddressType
import com.example.store.core.model.resource.isValidName
import com.example.store.core.model.resource.isValidPhoneNumber
import com.example.store.core.ui.component.CustomButton
import com.example.store.core.ui.component.StoreTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun AddressDetailsSheet(
    modifier: Modifier = Modifier,
    receiverName: String,
    phoneNumber: String,
    onReceiverNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    selectedAddressType: AddressType,
    onChooseAddressType: (AddressType) -> Unit,
    saveAddress: () -> Unit,
    onDismissRequest: () -> Unit
) {
    var isValidName by rememberSaveable { mutableStateOf(true) }
    var isValidPhoneNumber by rememberSaveable { mutableStateOf(true) }
    ModalBottomSheet(
        modifier = modifier,
        sheetState = rememberModalBottomSheetState(true),
        containerColor = MaterialTheme.colorScheme.surface.copy(0.99f),
        scrimColor = Color(0xFF000000).copy(0.3f),
        contentColor = MaterialTheme.colorScheme.onSurface,
        onDismissRequest = onDismissRequest,
        dragHandle = null,
        contentWindowInsets = { WindowInsets.navigationBars.union(WindowInsets.ime) }
    ) {
        val focusManager = LocalFocusManager.current

        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Detalhes do endereço",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Endereço completo nos ajudaria melhor a atendê-lo",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30))
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.outlineVariant,
                            RoundedCornerShape(30)
                        )
                        .clickable { onDismissRequest() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(22.dp),
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                    )
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AddressTypeSelector(
                    selectedAddressType = selectedAddressType,
                    onChooseAddressType = onChooseAddressType
                )
                StoreTextField(
                    modifier = Modifier,
                    value = receiverName,
                    placeholder = "Nome",
                    supportingText = "Mínimo 4 caracteres, letras apenas",
                    isError = !isValidName,
                    onValueChange = onReceiverNameChange,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Next) }
                    )
                )

                StoreTextField(
                    value = phoneNumber,
                    placeholder = "Contato ",
                    isError = !isValidPhoneNumber,
                    onValueChange = onPhoneNumberChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Salvar endereço",
                    onClick = {
                        isValidName = isValidName(receiverName)
                        isValidPhoneNumber = isValidPhoneNumber(phoneNumber)
                        if(isValidName && isValidPhoneNumber) {
                            saveAddress()
                        }
                    }
                )
            }
        }
    }
}



