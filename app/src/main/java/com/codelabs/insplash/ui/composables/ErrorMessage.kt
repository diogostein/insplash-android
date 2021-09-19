package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codelabs.insplash.R

@Composable
fun ErrorMessage(
    message: String? = null,
    color: Color = MaterialTheme.colors.secondary,
    onRetryTap: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            message ?: stringResource(R.string.an_error_occurred_please_try_again_later),
            textAlign = TextAlign.Center, color = color
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onRetryTap,
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            Text(stringResource(R.string.reload))
        }
    }
}