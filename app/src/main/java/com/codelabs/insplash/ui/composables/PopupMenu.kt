package com.codelabs.insplash.ui.composables

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily

@Composable
fun PopupMenu(
    modifier: Modifier = Modifier,
    menuItems: List<String>,
    onClickCallbacks: List<() -> Unit>,
    showMenu: Boolean,
    onDismiss: () -> Unit,
    toggle: @Composable () -> Unit,
) {
    DropdownMenu(
        modifier = modifier,
        expanded = showMenu,
        onDismissRequest = { onDismiss() },
    ) {
        menuItems.forEachIndexed { index, item ->
            DropdownMenuItem(onClick = {
                onDismiss()
                onClickCallbacks[index]()
            }) {
                Text(text = item, fontFamily = FontFamily.Default)
            }
        }
    }
}