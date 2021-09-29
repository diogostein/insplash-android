package com.codelabs.insplash.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
    val listState = rememberLazyListState()

    onOptionSelected(selectedValue ?: "")

    Box(contentAlignment = Alignment.Center) {
        LazyRow(
            state = listState,
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
        Visibility(visible = listState.firstVisibleItemIndex > 0) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                Color.Transparent
                            )
                        )
                    )
                    .align(Alignment.TopStart)
            )
        }
        Visibility(visible = showEndShadow(listState)) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(30.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colors.primary
                            )
                        )
                    )
                    .align(Alignment.TopEnd)
            )
        }
    }
}

private fun showEndShadow(listState: LazyListState) =
    listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size < listState.layoutInfo.totalItemsCount

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