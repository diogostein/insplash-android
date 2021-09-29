package com.codelabs.insplash.photos.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelabs.insplash.R

@Composable
fun AboutDialog(visible: MutableState<Boolean>) {
    val uriHandler = LocalUriHandler.current

    if (visible.value) {
        AlertDialog(
            onDismissRequest = { visible.value = false },
            title = {},
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        stringResource(R.string.app_name),
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 26.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(stringResource(R.string.developed_by_me),
                        color = MaterialTheme.colors.onPrimary,
                        fontFamily = FontFamily.Default,
                        fontSize = 12.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row {
                        TextButton(onClick = {
                            uriHandler.openUri("https://www.linkedin.com/in/diogo-ishihara-stein-2a912531/")
                        }) {
                            Text("LINKEDIN",
                                color = MaterialTheme.colors.onSurface,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        TextButton(onClick = {
                            uriHandler.openUri("https://github.com/diogostein")
                        }) {
                            Text("GITHUB",
                                color = MaterialTheme.colors.onSurface,
                                fontFamily = FontFamily.Default,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Text(stringResource(R.string.credits_to) + " ",
                            color = MaterialTheme.colors.onPrimary,
                            fontFamily = FontFamily.Default,
                            fontSize = 12.sp,
                        )
                        Text("Unsplash API",
                            color = MaterialTheme.colors.onPrimary,
                            fontFamily = FontFamily.Default,
                            fontSize = 12.sp,
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier.clickable {
                                uriHandler.openUri("https://unsplash.com/developers")
                            }
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { visible.value = false }) {
                    Text("OK",
                        color = MaterialTheme.colors.onSurface,
                        fontFamily = FontFamily.Default
                    )
                }
            },
        )
    }
}