package com.codelabs.insplash.ui.composables

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.codelabs.insplash.R
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomTopAppBar(
    onSearch: ((String) -> Unit)? = null,
    onCancelSearch: (() -> Unit)? = null,
    hideSearchField: Boolean = true,
    initialSearchText: String? = null,
    actions: (@Composable () -> Unit)? = null,
    bottomContent: (@Composable () -> Unit)? = null,
) {
    var showSearchField by remember { mutableStateOf(!hideSearchField) }
    var searchText by remember { mutableStateOf(initialSearchText ?: "") }

    if (hideSearchField) {
        searchText = ""
        showSearchField = false
    }

    Box {
        Surface(
            elevation = 8.dp,
            modifier = Modifier.statusBarsPadding(),
        ) {
            Column(
                modifier = Modifier.background(color = MaterialTheme.colors.primarySurface)
            ) {
                TopAppBar(
                    elevation = 0.dp,
                    title = {
                        Visibility(visible = !showSearchField) {
                            Text(stringResource(id = R.string.app_name))
                        }

                        Visibility(visible = showSearchField) {
                            SearchField(
                                searchText = searchText,
                                onValueChanged = { searchText = it },
                                onSearch = { onSearch?.invoke(it) },
                                onCancelSearch = { didSearch ->
                                    searchText = ""
                                    showSearchField = false
                                    if (didSearch) onCancelSearch?.invoke()
                                },
                            )
                        }
                    },
                    actions = {
                        Visibility(visible = !showSearchField) {
                            IconButton(onClick = {
                                showSearchField = true
                            }) {
                                Icon(Icons.Filled.Search, null)
                            }
                        }
                        actions?.invoke()
                    }
                )
                bottomContent?.invoke()
            }
        }
        Spacer(
            Modifier
                .background(MaterialTheme.colors.primarySurface)
                .statusBarsHeight()
                .fillMaxWidth()
        )
    }
}

@Composable
private fun SearchField(
    searchText: String,
    onValueChanged: (String) -> Unit,
    onSearch: (String) -> Unit,
    onCancelSearch: (Boolean) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    var didSearch by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose {}
    }

    TextField(
        value = searchText,
        onValueChange = onValueChanged,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        shape = RoundedCornerShape(4.dp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions {
            didSearch = true
            focusManager.clearFocus()
            onSearch.invoke(searchText)
        },
        textStyle = TextStyle.Default.copy(
            fontFamily = FontFamily.Default,
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.onPrimary,
        ),
        placeholder = {
            Text(stringResource(R.string.search_beautiful_photos), fontFamily = FontFamily.Default)
        },
        leadingIcon = {
            Icon(Icons.Filled.Search, stringResource(R.string.search))
        },
        trailingIcon = {
            IconButton(onClick = {
                onCancelSearch.invoke(didSearch)
                didSearch = false
            }) {
                Icon(Icons.Filled.Close, stringResource(R.string.close))
            }
        },
    )
}
