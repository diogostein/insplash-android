package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class ChipValue(val text: String, val value: String)

@Composable
fun ChipGroup(
    chipValues: List<ChipValue>,
    modifier: Modifier = Modifier,
    selectedValue: String? = null,
    onChanged: (String) -> Unit
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }

    onOptionSelected(selectedValue ?: "")

    LazyRow(
        modifier = modifier
    ) {
        item { Spacer(modifier = Modifier.width(6.dp)) }

        items(chipValues) { item ->
            ChipButton(item, selectedOption, onOptionSelected) {
                onChanged(it)
            }
        }

        item { Spacer(modifier = Modifier.width(6.dp)) }
    }
}

@Composable
private fun ChipButton(
    chipValue: ChipValue,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    onClick: (String) -> Unit
) {
    val isActive = selectedOption == chipValue.value

    Clickable(
        onClick = {
            (if (!isActive) chipValue.value else "").let {
                onOptionSelected(it)
                onClick(it)
            }
        },
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        Text(chipValue.text,
            style = MaterialTheme.typography.caption.copy(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Light,
                color = if (isActive) MaterialTheme.colors.primary else MaterialTheme.colors.onPrimary,
            ),
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = if (isActive) MaterialTheme.colors.secondary else MaterialTheme.colors.onPrimary,
                    shape = RoundedCornerShape(16.dp)
                )
                .background(color = if (isActive) MaterialTheme.colors.secondary else Color.Transparent)
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}