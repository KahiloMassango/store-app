package com.example.store.features.userprofile.helpcenter.contact_us


import com.example.store.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.store.features.userprofile.helpcenter.contact_us.components.ContactButton
import com.example.store.features.userprofile.helpcenter.contact_us.components.SocialMediaButton

@Composable
fun ContactUsContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = "Entre em contato",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                modifier = Modifier,
                text = "Sua satisfação é nossa prioridade. Entre em contato para qualquer dúvida.",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Spacer(modifier = Modifier.height(36.dp))
        ContactButton(
            contact = "+244 923 456 789",
            icon = Icons.Default.Call,
            onClick = { callToNumber(context) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        ContactButton(
            contact = "johndoe@example.com",
            icon = Icons.Outlined.Email,
            onClick = { contactByEmail(context) }
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            modifier = Modifier,
            text = "Redes Sociais",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SocialMediaButton(
                icon = R.drawable.ic_instagram,
                onClick = { /* TODO */ }
            )
            SocialMediaButton(
                icon = R.drawable.ic_facebook,
                onClick = { /* TODO */ }
            )
        }
    }
}



private fun callToNumber(context: Context) {
    val callingIntent = Intent(Intent.ACTION_DIAL).apply {
        setData(Uri.parse("tel:+244 923 456 789"))
    }
    context.startActivity(callingIntent)
}

private fun contactByEmail(context: Context) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        setData(Uri.parse("mailto:johndoe@example.com"))
    }
    context.startActivity(emailIntent)
}
